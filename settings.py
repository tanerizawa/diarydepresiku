# Backend Prototype - config/settings.py
# Configuration settings for the Flask application.

import os
from dotenv import load_dotenv

# Load environment variables from .env file
load_dotenv()

class Config:
    """Base configuration class."""
    # Secret key for session management, CSRF protection, etc.
    # Should be a long, random string and kept secret.
    SECRET_KEY = os.environ.get('SECRET_KEY') or 'a_very_secret_key_that_should_be_changed'

    # Database configuration
    # Example for PostgreSQL: 'postgresql://username:password@host:port/database_name'
    # Get the database URL from environment variables for security.
    SQLALCHEMY_DATABASE_URI = os.environ.get('DATABASE_URL') or 'sqlite:///../instance/app.db' # Default to SQLite for easy setup
    SQLALCHEMY_TRACK_MODIFICATIONS = False

    # JWT Configuration (Optional: Add more settings if needed)
    JWT_SECRET_KEY = os.environ.get('JWT_SECRET_KEY') or 'another_secret_jwt_key_change_this'

class DevelopmentConfig(Config):
    """Development specific configuration."""
    DEBUG = True

class ProductionConfig(Config):
    """Production specific configuration."""
    DEBUG = False
    # Add production-specific settings here, e.g., different database URI

# Dictionary to access config classes by name
config_by_name = {
    'development': DevelopmentConfig,
    'production': ProductionConfig,
    'default': DevelopmentConfig
}

