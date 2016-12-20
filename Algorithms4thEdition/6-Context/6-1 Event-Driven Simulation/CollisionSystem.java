import java.awt.Color;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

public class CollisionSystem {
	private final static double HZ = 0.5;
	
	private MinPQ<Event> pq;
	private double t=0.0;
	private Particle[] particles;
	
	public CollisionSystem(Particle[] particles){
		this.particles = particles.clone();
	}
	public void predict(Particle a,double limit){
		if (a == null) return;
		for(int i=0;i<particles.length;i++){
			double dt = a.timeToHit(particles[i]);
			if(t+dt<limit)
				pq.insert(new Event(t+dt, a, particles[i]));
		}
		
		double dtX = a.timeToHitVerticalWall();
		double dtY = a.timeToHitVerticalWall();
		if(t+dtX<limit)	pq.insert(new Event(t+dtX, a, null));
		if(t+dtY<limit)	pq.insert(new Event(t+dtY, null, a));
			
	}
	private void redraw(double limit) {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
        StdDraw.show(20);
        if (t < limit) {
            pq.insert(new Event(t + 1.0 / HZ, null, null));
        }
    }
	public void simulate(double limit){
		pq = new MinPQ<Event>();
		for(int i=0;i<particles.length;i++)
			predict(particles[i], limit);
		pq.insert(new Event(0, null, null));
		
		
		//key part lie here , it catches that particles collide not simultaneously
		//using priority queue to simulate every collision 
		//use class event to record count of collision preventing 
		//collision that got invalid due to previous collision
		
		while (!pq.isEmpty()) { 

            // get impending event, discard if invalidated
            Event e = pq.delMin();
            if (!e.isValid()) continue;
            Particle a = e.a;
            Particle b = e.b;

            // physical collision, so update positions, and then simulation clock
            for (int i = 0; i < particles.length; i++)
                particles[i].move(e.time - t);
            t = e.time;

            // process event
            if      (a != null && b != null) a.bounceOff(b);              // particle-particle collision
            else if (a != null && b == null) a.bounceOffVerticalWall();   // particle-wall collision
            else if (a == null && b != null) b.bounceOffHorizontalWall(); // particle-wall collision
            else if (a == null && b == null) redraw(limit);               // redraw event

            // update the priority queue with new collisions involving a or b
            predict(a, limit);
            predict(b, limit);
        }
	}
	private static class Event implements Comparable<Event>{
		private final double time;         // time that event is scheduled to occur
        private final Particle a, b;       // particles involved in event, possibly null
        private final int countA, countB;  // collision counts at event creation
        
        public Event(double t,Particle a,Particle b) {
        	this.time = t;
            this.a    = a;//safe the reference
            this.b    = b;//safe the reference
            if (a != null) countA = a.count();
            else           countA = -1;
            if (b != null) countB = b.count();
            else           countB = -1;
		}
        public int compareTo(Event that){
        	return Double.compare(this.time, that.time);
        }
        public boolean isValid(){
        	if(a!=null&&a.count()!=countA)	return false;
        	if(b!=null&&b.count()!=countB)	return false;
        	return true;
        }
	}
	public double temperature(){
		double sum = 0;
		for(int i=0;i<particles.length;i++)
			sum+=particles[i].temperature();
		return sum/particles.length;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StdDraw.setCanvasSize(800, 800);

        // remove the border
        // StdDraw.setXscale(1.0/22.0, 21.0/22.0);
        // StdDraw.setYscale(1.0/22.0, 21.0/22.0);

        // the array of particles
        Particle[] particles;

        // create n random particles
        if (args.length == 1) {
            int n = Integer.parseInt(args[0]);
            particles = new Particle[n];
            for (int i = 0; i < n; i++)
                particles[i] = new Particle();
        }

        // or read from standard input
        else {
            int n = StdIn.readInt();
            particles = new Particle[n];
            for (int i = 0; i < n; i++) {
                double rx     = StdIn.readDouble();
                double ry     = StdIn.readDouble();
                double vx     = StdIn.readDouble();
                double vy     = StdIn.readDouble();
                double radius = StdIn.readDouble();
                double mass   = StdIn.readDouble();
                int r         = StdIn.readInt();
                int g         = StdIn.readInt();
                int b         = StdIn.readInt();
                Color color   = new Color(r, g, b);
                particles[i] = new Particle(rx, ry, vx, vy, radius, mass, color);
            }
        }

        // create collision system and simulate
        CollisionSystem system = new CollisionSystem(particles);
        system.simulate(10000);
	}

}
