FROM gradle:6.8.1-jdk11

# install make, qemu-user and gcc-arm-linux-gnueabi
RUN apt-get update && apt-get install -y \
        make \
        qemu-user \
        gcc-arm-linux-gnueabi

# copy the files needed to build the wacc compiler into the container's file system
COPY . /wacc_18

WORKDIR /wacc_18

# builds the wacc compiler
RUN make

# runs the wacc compiler test when running this image
CMD ["make", "test"]
