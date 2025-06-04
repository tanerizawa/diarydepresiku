# Backend Prototype - app/routes/diary_routes.py
# Handles routes related to diary entries (creating, retrieving, etc.).

from flask import Blueprint, request, jsonify
from ..models import DiaryEntry, db # Import DiaryEntry model and db instance
from .auth_routes import token_required # Import the token_required decorator
from datetime import datetime

# Create a Blueprint for diary entry routes
diary_bp = Blueprint("diary_bp", __name__)

# --- Routes ---

@diary_bp.route("/entries", methods=["POST"])
@token_required # Protect this route: only logged-in users can create entries
def create_entry(current_user):
    """Creates a new diary entry for the logged-in user."""
    data = request.get_json()

    # Basic validation (can be expanded)
    if not data:
        return jsonify({"message": "No input data provided"}), 400

    emotion_category = data.get("emotion_category")
    emotion_intensity = data.get("emotion_intensity")
    notes = data.get("notes")
    entry_timestamp_str = data.get("entry_timestamp") # Optional: client can provide timestamp

    # Use provided timestamp or default to now
    if entry_timestamp_str:
        try:
            # Example format: "YYYY-MM-DD HH:MM:SS" (adjust as needed based on client format)
            entry_timestamp = datetime.strptime(entry_timestamp_str, "%Y-%m-%d %H:%M:%S")
        except ValueError:
            entry_timestamp = datetime.utcnow() # Default if format is wrong
    else:
        entry_timestamp = datetime.utcnow()

    # Create the new entry associated with the current user
    new_entry = DiaryEntry(
        user_id=current_user.id,
        entry_timestamp=entry_timestamp,
        emotion_category=emotion_category,
        emotion_intensity=emotion_intensity,
        notes=notes
        # Add other fields like location, activity later
    )

    try:
        db.session.add(new_entry)
        db.session.commit()
        # Return the created entry data (optional)
        return jsonify({
            "message": "Diary entry created successfully",
            "entry": {
                "id": new_entry.id,
                "entry_timestamp": new_entry.entry_timestamp.isoformat(),
                "emotion_category": new_entry.emotion_category,
                "emotion_intensity": new_entry.emotion_intensity,
                "notes": new_entry.notes
            }
        }), 201 # Created
    except Exception as e:
        db.session.rollback()
        return jsonify({"message": "Failed to create diary entry", "error": str(e)}), 500

@diary_bp.route("/entries", methods=["GET"])
@token_required # Protect this route
def get_entries(current_user):
    """Retrieves all diary entries for the logged-in user."""
    try:
        # Query entries for the current user, order by timestamp descending
        # Add pagination later for performance (e.g., using request.args.get("page"))
        entries = DiaryEntry.query.filter_by(user_id=current_user.id).order_by(DiaryEntry.entry_timestamp.desc()).all()

        # Serialize the entries into a list of dictionaries
        output = []
        for entry in entries:
            entry_data = {
                "id": entry.id,
                "entry_timestamp": entry.entry_timestamp.isoformat() + "Z", # ISO format with Z for UTC
                "emotion_category": entry.emotion_category,
                "emotion_intensity": entry.emotion_intensity,
                "notes": entry.notes
                # Add other fields as needed
            }
            output.append(entry_data)

        return jsonify({"entries": output}), 200
    except Exception as e:
        return jsonify({"message": "Failed to retrieve entries", "error": str(e)}), 500

# Potential future routes:
# - GET /entries/<int:entry_id> (Retrieve a specific entry)
# - PUT /entries/<int:entry_id> (Update an entry)
# - DELETE /entries/<int:entry_id> (Delete an entry)

