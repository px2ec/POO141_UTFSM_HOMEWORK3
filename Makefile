JC = javac
JVM = java
JDOC = javadoc
JFLAGS = -g

JAR = jar
JARFLAGS = cmf

CLASSES = \
	Ball.class \
	BallView.class \
	Block.class \
	BlockView.class \
	MyWorld.class \
	PhysicsLab.class \
	PhysicsLabApplet.class \
	PhysicsLabClass.class \
	PhysicsElement.class \
	FixedHook.class \
	FixedHookView.class \
	Spring.class \
	SpringView.class \
	Rubber.class \
	RubberView.class \
	Oscillator.class \
	OscillatorView.class \
	SpringAttachable.class\
	LabMenuListener.class \
	Simulateable.class \
	MyWorldView.class \
	MouseListener.class \
	Elastic.class

SOURCES = \
	Ball.java \
	BallView.java \
	Block.java \
	BlockView.java \
	MyWorld.java \
	PhysicsLab.java \
	PhysicsLabApplet.java \
	PhysicsLabClass.java \
	PhysicsElement.java \
	FixedHook.java \
	FixedHookView.java \
	Spring.java \
	SpringView.java \
	Rubber.java \
	RubberView.java \
	Oscillator.java \
	OscillatorView.java \
	SpringAttachable.java\
	LabMenuListener.java \
	Simulateable.java \
	MyWorldView.java \
	MouseListener.java \
	Elastic.java

MAIN = PhysicsLab

JARFILE = $(MAIN).jar

.SUFFIXES: .java .class .jar

.java.class:
	$(JC) $(JFLAGS) $<

.java.jar: $(CLASSES)
	echo Main-Class: $(MAIN) > MANIFEST.mf
	$(JAR) $(JARFLAGS) MANIFEST.mf $(JARFILE) *.class

all: $(CLASSES) $(JARFILE)

doc: $(SOURCES)
	$(JDOC) -author $(SOURCES) -d Documentation

runclass: $(MAIN).class
	$(JVM) $(MAIN)

run: $(JARFILE)
	$(JVM) -$(JAR) $(JARFILE)

clean:
	rm -rf *.class *.jar

mrproper:
	make clean
	rm -rf res*.csv res*.png

rebuild:
	make clean && make

rerun:
	make rebuild && make run

runApplet:
	appletviewer PhysicsLab.html
