board name=buildTest gravity=29.8 friction1=0.0 friction2=0.0
# This is a test for the builder. The board is quite boring.

ball name=Ball1 x=10 y=5 xVelocity=0 yVelocity=-10
absorber name=Absorber x=3 y=16 width=5 height=2
squareBumper name=Square x=4 y=12
circleBumper name=Circle x=5 y=12
triangleBumper name=Triangle x=7 y=12 orientation=90
rightFlipper name=FlipperR x=5 y=5 orientation=180
portal name=Portal1b x=10 y=5 otherBoard=buildTest2 otherPortal=Portal2a
portal name=Portal2b x=4 y=1 otherBoard=buildTest2 otherPortal=Portal1a
#fire trigger=Square action=Absorber
#fire trigger=Circle action=Absorber
fire trigger=Absorber action=Absorber