public class Body {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double deltaX = this.xxPos - b.xxPos;
        double deltaY = this.yyPos - b.yyPos;
        return Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));
    }

    public double calcForceExertedBy(Body b){
        double G = 6.67e-11;
        return G * this.mass * b.mass / Math.pow(this.calcDistance(b),2);
    }

    public double calcForceExertedByX(Body b){
        double dX = b.xxPos - this.xxPos;
        return this.calcForceExertedBy(b) * dX / this.calcDistance(b);
    }

    public double calcForceExertedByY(Body b){
        double dY = b.yyPos - this.yyPos;
        return this.calcForceExertedBy(b) * dY / this.calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] bodies){
        double fNet = 0;
        for (Body item: bodies) {
            if(!this.equals(item)){
                fNet+=this.calcForceExertedByX(item);
            }
        }
        return fNet;
    }

    public double calcNetForceExertedByY(Body[] bodies){
        double fNet = 0;
        for (Body item: bodies) {
            if(!this.equals(item)){
                fNet+=this.calcForceExertedByY(item);
            }
        }
        return fNet;
    }

    public void update(double t, double fX, double fY){
        xxVel = xxVel + t * fX / mass;
        yyVel = yyVel + t * fY / mass;
        xxPos = xxPos + t * xxVel;
        yyPos = yyPos + t * yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
