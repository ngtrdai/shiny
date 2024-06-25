from fastapi import Depends
from fastapi.security import OAuth2PasswordBearer

oauth2_scheme = OAuth2PasswordBearer(tokenUrl="/auth/token")


def authenticate_user(username: str, password: str):
    # Code to authenticate user against Keycloak
    pass


def get_current_user(token: str = Depends(oauth2_scheme)):
    # Code to validate token with Keycloak and return the user
    pass
