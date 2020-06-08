package br.com.marcelphilippe.bis14vs100meteoros.interfaces;

import br.com.marcelphilippe.bis14vs100meteoros.objects.Meteor;

public interface MeteorsEngineDelegate {
    public void createMeteor(Meteor meteor, float x, float y, float vel, double ang, int vl);

    public void createMeteor(Meteor meteor);
}
