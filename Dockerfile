# Use a small base image
FROM busybox

# Copy the local files to the container's working directory
COPY . /app

# Set the working directory
WORKDIR /app

# Define the default command to run
CMD ["sh"]