# Backend Prototype - app/models.py
# Defines the database models using Flask-SQLAlchemy.

from . import db, bcrypt # Import db and bcrypt from the app package (__init__.py)
from datetime import datetime

class User(db.Model):
    """User model for storing user account information."""
    __tablename__ = "users" # Explicitly naming the table

    id = db.Column(db.Integer, primary_key=True)
    email = db.Column(db.String(255), unique=True, nullable=False, index=True)
    password_hash = db.Column(db.String(128), nullable=False)
    display_name = db.Column(db.String(100), nullable=True)
    created_at = db.Column(db.DateTime, default=datetime.utcnow)
    updated_at = db.Column(db.DateTime, default=datetime.utcnow, onupdate=datetime.utcnow)

    # Relationship to DiaryEntry (one-to-many)
    # lazy="dynamic" allows querying entries for a user
    diary_entries = db.relationship("DiaryEntry", backref="author", lazy="dynamic", cascade="all, delete-orphan")

    def set_password(self, password):
        """Hashes the password and stores it."""
        self.password_hash = bcrypt.generate_password_hash(password).decode("utf-8")

    def check_password(self, password):
        """Checks if the provided password matches the stored hash."""
        return bcrypt.check_password_hash(self.password_hash, password)

    def __repr__(self):
        return f"<User {self.email}>"

class DiaryEntry(db.Model):
    """DiaryEntry model for storing user diary entries."""
    __tablename__ = "diary_entries" # Explicitly naming the table

    id = db.Column(db.Integer, primary_key=True)
    user_id = db.Column(db.Integer, db.ForeignKey("users.id"), nullable=False, index=True)
    entry_timestamp = db.Column(db.DateTime, nullable=False, default=datetime.utcnow, index=True)
    emotion_category = db.Column(db.String(50), nullable=True) # e.g., "Happy", "Sad", "Anxious"
    emotion_intensity = db.Column(db.Integer, nullable=True) # e.g., scale 1-5
    notes = db.Column(db.Text, nullable=True) # Free text entry
    # Potential future fields (from proposal):
    # detected_location_type = db.Column(db.String(100), nullable=True)
    # detected_activity_type = db.Column(db.String(100), nullable=True)
    # heart_rate_bpm = db.Column(db.Integer, nullable=True)
    created_at = db.Column(db.DateTime, default=datetime.utcnow)
    updated_at = db.Column(db.DateTime, default=datetime.utcnow, onupdate=datetime.utcnow)

    def __repr__(self):
        return f"<DiaryEntry {self.id} by User {self.user_id} at {self.entry_timestamp}>"

# Note: Other models like PsychologicalTest, TestResult, AIInsight, etc.,
# would be added here following a similar pattern as development progresses.

