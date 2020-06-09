package br.com.marcelphilippe.bis14vs100meteoros.interfaces;

import br.com.marcelphilippe.bis14vs100meteoros.objects.Shoot;

public interface ShootEngineDelegate {
    public void createShoot(Shoot shoot);

    public void removeShoot(Shoot shoot);
}
