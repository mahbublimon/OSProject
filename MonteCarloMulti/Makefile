CXX = g++
CXXFLAGS = -std=c++11 -pthread -Wall -O3
TARGET = MonteCarloMulti
SRC_DIR = src
SRC = $(SRC_DIR)/main.cpp

all: $(TARGET)

$(TARGET): $(SRC)
	$(CXX) $(CXXFLAGS) -o $@ $^

clean:
	rm -f $(TARGET)

.PHONY: all clean