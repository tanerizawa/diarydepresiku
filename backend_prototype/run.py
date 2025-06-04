# Backend Prototype - run.py
# Entry point script to run the Flask development server.

from app import create_app, db # Import the factory function and db instance
from flask_migrate import Migrate # Import Migrate
import os

# Determine the configuration to use (e.g., from environment variable)
# Defaults to 'development' if not set
config_name = os.getenv('FLASK_CONFIG') or 'default'

# Create the Flask app instance using the factory
app = create_app(config_name)

# Initialize Migrate with the app and db
migrate = Migrate(app, db)

# Optional: Add context processors or other app-level configurations here if needed

# Define the main execution block
if __name__ == '__main__':
    # Use Flask's built-in development server
    # host='0.0.0.0' makes the server accessible externally (within the sandbox network)
    # debug=True enables debug mode (should be False in production)
    app.run(host='0.0.0.0', port=5000, debug=app.config['DEBUG'])

# To run the app:
# 1. Set up a virtual environment: python -m venv venv
# 2. Activate it: source venv/bin/activate (Linux/macOS) or venv\Scripts\activate (Windows)
# 3. Install dependencies: pip install -r requirements.txt
# 4. Set environment variables (e.g., in .env file):
#    SECRET_KEY='your_secret_key'
#    JWT_SECRET_KEY='your_jwt_secret_key'
#    DATABASE_URL='your_database_connection_string' (e.g., 'postgresql://user:pass@host/db')
#    FLASK_CONFIG='development' (optional)
# 5. Initialize/Migrate database (first time or after model changes):
#    flask db init (only first time)
#    flask db migrate -m "Initial migration." (or descriptive message)
#    flask db upgrade
# 6. Run the server: python run.py

