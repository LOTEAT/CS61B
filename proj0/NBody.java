import java.util.Arrays;

public class NBody {
    // read radius
    public static double readRadius(String filename){
        In in = new In(filename);
        // read N
        in.readInt();
        // read radius
        return in.readDouble();
    }

    // read planets from a file
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        // read N
        int N = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[N];
        for(int i=0; i<N; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xP, yP, xV, yV, mass, img);
        }
        return planets;
    }



    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        int planets_num = planets.length;
        double[] xForces = new double[planets_num];
        double[] yForces = new double[planets_num];
        for(int t=0; t<=T; t+=dt){
            for(int i=0; i<planets_num; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(int i=0; i<planets_num; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(0);
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
