public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    public static Body[] readBodies(String fileName){
        In in = new In(fileName);
        int num = in.readInt();
        Body[] bodies = new Body[num];
        in.readDouble();
        for(int i = 0; i < num; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return bodies;
    }

    public static String imageToDraw = "images/starfield.jpg";

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dT = Double.parseDouble(args[1]);
        String fileName = args[2];
        Double radius = readRadius(fileName);
        Body[] bodies = readBodies(fileName);

        setBackground(radius);

        for (Body item: bodies) {
            item.draw();
        }

        StdDraw.enableDoubleBuffering();

        for (double time = 0; time < T; time+=dT) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dT, xForces[i],yForces[i]);
            }
            StdDraw.picture(0,0,imageToDraw);
            for (Body item:bodies) {
                item.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }

    private static void setBackground(Double radius){
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0,0,imageToDraw);
    }
}
