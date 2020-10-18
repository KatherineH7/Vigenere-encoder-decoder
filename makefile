run:decoder.class
	java decoder $(ARGS)

decoder.class: decoder.java
	javac decoder.java

clean:
	rm *.class
