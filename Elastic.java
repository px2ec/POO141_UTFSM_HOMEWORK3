interface Elastic {
	void attachAend(SpringAttachable sa);
	void attachBend(SpringAttachable sa);
	boolean isAattachedTo(SpringAttachable sa);	
	boolean isBattachedTo(SpringAttachable sa);
	void detachAend();
	void detachBend();
	double getAendPosition();
	double getBendPosition();
	double getRestLength();
	double getForce(SpringAttachable sa);
	double getPotential();
}