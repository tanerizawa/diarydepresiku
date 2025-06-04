# Backend Prototype Structure

backend_prototype/
├── app/                  # Main application folder
│   ├── __init__.py       # Initialize Flask app and extensions
│   ├── models.py         # Database models (SQLAlchemy)
│   ├── routes/           # Route blueprints
│   │   ├── __init__.py   # Routes package init
│   │   ├── auth_routes.py # Authentication routes (login, register)
│   │   └── diary_routes.py # Diary entry routes
│   └── utils/            # Utility functions (optional, e.g., helpers)
│       └── __init__.py
├── config/               # Configuration files
│   └── __init__.py
│   └── settings.py       # Application settings (DB URI, Secret Key, etc.)
├── migrations/           # Database migration scripts (Flask-Migrate)
├── requirements.txt      # Python package dependencies
├── .env                  # Environment variables (for sensitive data like DB pass, API keys - DO NOT COMMIT)
└── run.py                # Script to run the Flask development server

