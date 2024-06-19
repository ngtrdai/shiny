from fastapi import APIRouter

router = APIRouter(prefix="/health", tags=["Health Check"])


@router.get("/")
async def health_check():
    return {"message": "I'm alive!"}
