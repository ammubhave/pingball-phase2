board name=sampleBoard6 gravity=20.0 friction1=0.020 friction2=0.020

  # define a ball
  ball name=Ball x=1.5 y=1.5 xVelocity=2.5 yVelocity=2.5

  # define a series of square bumpers
  squareBumper name=Square0 x=0 y=4
  squareBumper name=Square1 x=1 y=4
  squareBumper name=Square2 x=2 y=4
  squareBumper name=Square3 x=3 y=4
  squareBumper name=Square4 x=4 y=4
  squareBumper name=Square5 x=5 y=4
  squareBumper name=Square6 x=6 y=4
  squareBumper name=Square7 x=7 y=4
  
  squareBumper name=Square12 x=12 y=2
  squareBumper name=Square13 x=13 y=2
  squareBumper name=Square14 x=14 y=2
  squareBumper name=Square15 x=15 y=2
  squareBumper name=Square16 x=16 y=2
  squareBumper name=Square17 x=17 y=2
  squareBumper name=Square18 x=18 y=2
  squareBumper name=Square19 x=19 y=2
  
  squareBumper name=Square123 x=12 y=11
  squareBumper name=Square133 x=13 y=11
  squareBumper name=Square143 x=14 y=11
  squareBumper name=Square153 x=15 y=11
  squareBumper name=Square163 x=16 y=11
  squareBumper name=Square173 x=17 y=11
  squareBumper name=Square183 x=18 y=11
  squareBumper name=Square193 x=19 y=11
  
  # define a series of circle bumpers
  circleBumper name=Circle6 x=6 y=5
  circleBumper name=Circle7 x=7 y=6
  
  # define some triangular bumpers
  triangleBumper name=Tri2 x=10 y=9 orientation=90
  
  # add some flippers
  leftFlipper name=FlipL2 x=8 y=7 orientation=90
  rightFlipper name=FlipR2 x=10 y=7 orientation=90

  # define an absorber to catch the ball at the bottom
  absorber name=Abs x=0 y=19 width=20 height=1 

  # make the absorber self-triggering
  fire trigger=Abs action=Abs 
