
public class Planet {
    // Its current x position
    public double xxPos;
    // Its current y position
    public double yyPos;
    // Its current velocity in the x direction
    public double xxVel;
    // Its current velocity in the y direction
    public double yyVel;
    // Its mass
    public double mass;
    // The name of the file that corresponds to the image that depicts the body
    public String imgFileName;
    public double G = 6.67E-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    // calculate the distance between two planets
    public double calcDistance(Planet p){
        return Math.sqrt(Math.pow(xxPos - p.xxPos, 2) + Math.pow(yyPos - p.yyPos, 2));
    }

    // calculate the force from a planet
    public double calcForceExertedBy(Planet p){
        double distance = calcDistance(p);
        if(distance == 0)
            return 0;
        return G * mass * p.mass / (distance * distance);
    }

    // calculate the x-force from a planet
    public double calcForceExertedByX(Planet p){
        double dis = calcDistance(p);
        if(dis == 0)
            return 0;
        double disX = p.xxPos - xxPos;
        double force = calcForceExertedBy(p);
        return disX / dis * force;
    }

    // calculate the y-force from a planet
    public double calcForceExertedByY(Planet p){
        double dis = calcDistance(p);
        if(dis == 0)
            return 0;
        double disY = p.yyPos - yyPos;
        double force = calcForceExertedBy(p);
        return disY / dis * force;
    }

    // calculate x-force from planets
    public double calcNetForceExertedByX(Planet[] planets){
        double netForceX = 0;
        for (Planet planet : planets) {
            netForceX += calcForceExertedByX(planet);
        }
        return netForceX;
    }

    // calculate y-force from planets
    public double calcNetForceExertedByY(Planet[] planets){
        double netForceY = 0;
        for (Planet planet : planets) {
            netForceY += calcForceExertedByY(planet);
        }
        return netForceY;
    }

    // update speed and position
    public void update(double dt, double fX, double fY){
        double a_net_x = fX / mass;
        double a_net_y = fY / mass;
        xxVel += a_net_x * dt;
        yyVel += a_net_y * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel *dt;
    }

    // draw
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }


}