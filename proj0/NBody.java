/**
 * @author : wendi
 * @description: TODO
 * @date :2022/3/5 21:44
 */

public class NBody {
    public static double readRadius(String file){
        In in = new In(file);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int nums=in.readInt();
        Planet[] result=new Planet[nums];
        double radius=in.readDouble();
        for(int i=0;i<nums;++i){
            double xxPos=in.readDouble();
            double yyPos=in.readDouble();
            double xxVel=in.readDouble();
            double yyVel=in.readDouble();
            double mass=in.readDouble();
            String img=in.readString();
            Planet curr=new Planet(xxPos,yyPos,xxVel,yyVel,mass,img);
            result[i]=curr;
        }
        return result;
    }

    public static void main(String args[]){
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double radius=NBody.readRadius(filename);
        Planet[] planets=NBody.readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        int planet_nums=planets.length;
        double xForces[]=new double[planet_nums];
        double yForces[]=new double[planet_nums];

        for(double t=0;t<=T;t+=dt){
            for(int i=0;i<planet_nums;++i){
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);

            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(int i=0;i<planet_nums;++i){
                planets[i].update(dt, xForces[i], yForces[i]);
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}
