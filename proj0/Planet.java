/**
 * @author : wendi
 * @description: TODO
 * @date :2022/3/5 20:22
 */
public class Planet {

    //cur x position
    public double xxPos;
    //cur y position
    public double yyPos;
    //cur velocity in x direction
    public double xxVel;
    //cur velocity in y direction
    public double yyVel;
    //mass
    public double mass;
    public String imgFileName;

    public Planet(double xP,double yP,double xV,double yV,double m,String img){
        this.xxPos=xP;
        this.yyPos=yP;
        this.xxVel=xV;
        this.yyVel=yV;
        this.mass=m;
        this.imgFileName=img;
    }

    public Planet(Planet p){
        this.xxPos=p.xxPos;
        this.yyPos=p.yyPos;
        this.xxVel=p.xxVel;
        this.yyVel=p.yyVel;
        this.mass=p.mass;
        this.imgFileName=p.imgFileName;
    }

    public double calcDistance(Planet p){
        double distance=Math.sqrt((p.yyPos-this.yyPos)*(p.yyPos-this.yyPos)+(p.xxPos-this.xxPos)*(p.xxPos-this.xxPos));
        return distance;
    }

    public double calcForceExertedBy(Planet p){
        double distance=this.calcDistance(p);
        double G=6.67e-11;
        double force=G*this.mass*p.mass/(distance*distance);
        return force;
    }

    public double calcForceExertedByX(Planet p){
        double force=this.calcForceExertedBy(p);
        double distance=this.calcDistance(p);
        double force_x=force*(p.xxPos-this.xxPos)/distance;
        return force_x;
    }

    public double calcForceExertedByY(Planet p){
        double force=this.calcForceExertedBy(p);
        double distance=this.calcDistance(p);
        double force_y=force*(p.yyPos-this.yyPos)/distance;
        return force_y;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double netForce_X=0.0;
        for(Planet p:allPlanets){
            if(this.equals(p)){
                continue;
            }
            double force_x=this.calcForceExertedByX(p);
            netForce_X+=force_x;
        }
        return netForce_X;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double netForce_Y=0.0;
        for(Planet p:allPlanets){
            if(this.equals(p)){
                continue;
            }
            double force_y=this.calcForceExertedByY(p);
            netForce_Y+=force_y;
        }
        return netForce_Y;
    }

    public void update(double dt, double fX, double fY){
        double aX=fX/this.mass;
        double aY=fY/this.mass;
        double vnewX=this.xxVel+dt*aX;
        double vnewY=this.yyVel+dt*aY;
        double pnewX=this.xxPos+dt*vnewX;
        double pnewY=this.yyPos+dt*vnewY;
        xxVel=vnewX;
        yyVel=vnewY;
        xxPos=pnewX;
        yyPos=pnewY;
        return;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos,"images/"+imgFileName);
    }
}
