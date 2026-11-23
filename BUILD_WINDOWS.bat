@echo off
setlocal
gradle --no-daemon build
if errorlevel 1 (
  echo Build failed. Check the first error above.
  pause
  exit /b 1
)
echo Build complete. Your mod JAR is in build\libs\
pause
