import uvicorn
from fastapi import FastAPI
from starlette.responses import RedirectResponse

from constants import app_constant
from routers import health_check, authentication

app = FastAPI(
    title=app_constant.APP_NAME,
    description=app_constant.APP_DESC
)


app.include_router(authentication.router, prefix='/auth')
app.include_router(health_check.router, prefix=app_constant.API_V1_PREFIX)


@app.get("/")
async def docs_redirect():
    return RedirectResponse(url="/docs")


if __name__ == "__main__":
    uvicorn.run("main:app", host="0.0.0.0", port=8000, log_level="info", reload=True)
