from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_bcrypt import Bcrypt

# Configuration settings live in the sibling `config` package
from config.settings import config_by_name

# Extensions

db = SQLAlchemy()
bcrypt = Bcrypt()

def create_app(config_name: str = 'default') -> Flask:
    """Application factory for the Flask app."""
    app = Flask(__name__)
    app.config.from_object(config_by_name[config_name])

    db.init_app(app)
    bcrypt.init_app(app)

    from .routes.auth_routes import auth_bp
    from .routes.diary_routes import diary_bp

    app.register_blueprint(auth_bp, url_prefix='/auth')
    app.register_blueprint(diary_bp, url_prefix='/diary')

    return app
