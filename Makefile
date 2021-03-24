# Makefile for the WACC Compiler lab

# Locations
ANTLR_DIR	:= antlr_config
SOURCE_DIR := src

# Tools
ANTLR	:= antlrBuild
RM	:= rm -rf

# the make rules
all:
	cd $(ANTLR_DIR) && ./$(ANTLR)
	./gradlew compileJava

test: all
	[ -z "$(TESTCLASS)" ] && ./gradlew test || ./gradlew test --tests $(TESTCLASS)

clean:
	$(RM) *.s
	$(RM) *.exe
	$(RM) $(SOURCE_DIR)/main/antlr
	gradle clean

.PHONY: all test clean


