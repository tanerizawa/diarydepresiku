# Backend Prototype - app/routes/auth_routes.py
# Handles authentication routes like user registration and login.

from flask import Blueprint, request, jsonify
from ..models import User, db # Import User model and db instance
from .. import bcrypt # Import bcrypt instance
import jwt # For JWT handling
from functools import wraps
from flask import current_app # To access app config like JWT_SECRET_KEY
import datetime

# Create a Blueprint for authentication routes
auth_bp = Blueprint(\"auth_bp\", __name__)

# --- Helper Function for Token Generation ---
def generate_token(user_id):
    """Generates a JWT token for a given user ID."""
    try:
        payload = {
            \"exp\": datetime.datetime.utcnow() + datetime.timedelta(days=1), # Token expiration (e.g., 1 day)
            \"iat\": datetime.datetime.utcnow(), # Issued at time
            \"sub\": user_id # Subject (user ID)
        }
        return jwt.encode(
            payload,
            current_app.config.get(\"JWT_SECRET_KEY\"),
            algorithm=\"HS256\"
        )
    except Exception as e:
        return str(e)

# --- Routes ---

@auth_bp.route(\"/register\", methods=[\"POST\"])
def register():
    """User registration endpoint."""
    data = request.get_json()
    if not data or not data.get(\"email\") or not data.get(\"password\"):
        return jsonify({\"message\": \"Email and password are required\"}), 400

    # Check if user already exists
    if User.query.filter_by(email=data.get(\"email\")).first():
        return jsonify({\"message\": \"User already exists\"}), 409 # Conflict

    # Create new user
    new_user = User(email=data.get(\"email\"), display_name=data.get(\"display_name\"))
    new_user.set_password(data.get(\"password\"))

    try:
        db.session.add(new_user)
        db.session.commit()
        return jsonify({\"message\": \"User registered successfully\"}), 201 # Created
    except Exception as e:
        db.session.rollback()
        return jsonify({\"message\": \"Registration failed\", \"error\": str(e)}), 500

@auth_bp.route(\"/login\", methods=[\"POST\"])
def login():
    """User login endpoint."""
    data = request.get_json()
    if not data or not data.get(\"email\") or not data.get(\"password\"):
        return jsonify({\"message\": \"Email and password are required\"}), 400

    user = User.query.filter_by(email=data.get(\"email\")).first()

    # Check if user exists and password is correct
    if not user or not user.check_password(data.get(\"password\")):
        return jsonify({\"message\": \"Invalid email or password\"}), 401 # Unauthorized

    # Generate JWT token
    token = generate_token(user.id)
    if isinstance(token, str) and not token.startswith(\"ey\"): # Basic check if token generation failed
         return jsonify({\"message\": \"Could not generate token\", \"error\": token}), 500

    return jsonify({
        \"message\": \"Login successful\",
        \"token\": token,
        \"user\": {
            \"id\": user.id,
            \"email\": user.email,
            \"display_name\": user.display_name
        }
    }), 200

# --- Decorator for Token Verification (Example) ---
def token_required(f):
    """Decorator to protect routes that require authentication."""
    @wraps(f)
    def decorated(*args, **kwargs):
        token = None
        # Check if token is in the header
        if \"Authorization\" in request.headers:
            auth_header = request.headers[\"Authorization\"]
            try:
                # Expecting \"Bearer <token>\"
                token = auth_header.split(\" \")[1]
            except IndexError:
                return jsonify({\"message\": \"Bearer token malformed\"}), 401

        if not token:
            return jsonify({\"message\": \"Token is missing!\"}), 401

        try:
            # Decode the token using the secret key
            data = jwt.decode(token, current_app.config.get(\"JWT_SECRET_KEY\"), algorithms=[\"HS256\"])
            current_user = User.query.get(data[\"sub\"])
            if not current_user:
                 return jsonify({\"message\": \"User not found\"}), 401
        except jwt.ExpiredSignatureError:
            return jsonify({\"message\": \"Token has expired!\"}), 401
        except jwt.InvalidTokenError:
            return jsonify({\"message\": \"Token is invalid!\"}), 401
        except Exception as e:
             return jsonify({\"message\": \"Token validation error\", \"error\": str(e)}), 401

        # Pass the current user to the decorated function
        return f(current_user, *args, **kwargs)
    return decorated

