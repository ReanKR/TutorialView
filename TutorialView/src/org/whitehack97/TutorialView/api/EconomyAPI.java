package org.whitehack97.TutorialView.api;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.whitehack97.TutorialView.TutorialView;

public class EconomyAPI
{
  public static Economy getEconomy()
  {
    Economy ecoHook = null;

    RegisteredServiceProvider<Economy> economyProvider = TutorialView.plugin.getServer().getServicesManager().getRegistration(Economy.class);
    if (economyProvider != null)
    {
      ecoHook = (Economy)economyProvider.getProvider();
    }
    return ecoHook;
  }
}
