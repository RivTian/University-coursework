cmake_minimum_required(VERSION 3.21)
project(tqdm_cpp)

set(CMAKE_CXX_STANDARD 17)

add_executable(tqdm main.cpp tqdm.hpp)
