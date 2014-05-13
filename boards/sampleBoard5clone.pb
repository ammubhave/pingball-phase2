board name=buildTest2 gravity=29.8 friction1=0.0 friction2=0.0
# This is a test for the builder. The board is quite boring.

ball name=Ball x=10 y=5 xVelocity=0 yVelocity=-10
absorber name=Absorber x=3 y=16 width=5 height=2
squareBumper name=Square x=4 y=12
circleBumper name=Circle x=5 y=12
triangleBumper name=Triangle x=7 y=12 orientation=90
rightFlipper name=FlipperR x=5 y=5 orientation=180
portal name=Portal1 x=10 y=5 otherBoard=buildTest otherPortal=Portal2
portal name=Portal2 x=4 y=1 otherBoard=buildTest otherPortal=Portal1
#fire trigger=Square action=Absorber
#fire trigger=Circle action=Absorber
fire trigger=Absorber action=Absorber