# Read me....
# To run this in bluej, control-click "<<applet>> LifeGame" and choose method "main( )". 
(Use the default {} that it supplies as parameter to main.)

# To compile with terminal, cd to this directory and merely
javac ComponentUtil.java LifeGame.java
# or see deprecation warnings:
javac -Xlint ComponentUtil.java LifeGame.java

# Can run by browsing the "lifegame.html"
# Or, to run from terminal, merely
java LifeGame
# Note: not saying ".class" !!
# Don't have to say full:  java LifeGame LifeBoard ComponentUtil
# The version with listeners does not need to mention the LifeGame$1 etc.


# Bugs...
[ ]get neighborcount is giving bad results in small world: perhaps the toroidal counting


# Current issues...
[ ]Gets ugly layout after reading in a scenario from a file. Resize by hand to fix.


# Future Plans:
[ ]rule classes and rule factorys, would use a factory that returns a rule to read rule from file, eg.

[ ]for bad arguments might use   		throw new IllegalArgumentException( "bad argument X=" + X ); 
[ ]see Golly in reaktor which plays life game as sine wave synthesizer
# [ ]Click to draw/erase cells.


# Fixed/History
# 2011 Nov 17 fixed privileges on github  friday-programming/life
# 2011 Oct 21
[x]brought in Gavin's speed slider and processing thread

# 2011 Sep 20
# [x]"Get File" button was causing other buttons to get hidden or not repainted?
# [x]Wasn't running from html because constructor wasn't public.
# [x]Window didn't show up in standalone. Double boo-boo: init was calling init, and constructor was calling init which called constructor.
# [x]Deprecated "action( )" has been replaced with actionListeners.
