package keystrokesmod.client.module.modules.combat;

import keystrokesmod.client.module.Module;
import keystrokesmod.client.module.SliderSetting;
import keystrokesmod.client.module.TickSetting;
import keystrokesmod.client.utils.Utils;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import keystrokesmod.client.lib.fr.jmraich.rax.event.FMLEvent;
import org.lwjgl.input.Keyboard;

public class Velocity extends Module {
   public static SliderSetting a;
   public static SliderSetting b;
   public static SliderSetting c;
   public static TickSetting d;
   public static TickSetting e;

   public Velocity() {
      super("Velocity", Module.category.combat, 0);
      this.registerSetting(a = new SliderSetting("Horizontal", 90.0D, 0.0D, 100.0D, 1.0D));
      this.registerSetting(b = new SliderSetting("Vertical", 100.0D, 0.0D, 100.0D, 1.0D));
      this.registerSetting(c = new SliderSetting("Chance", 100.0D, 0.0D, 100.0D, 1.0D));
      this.registerSetting(d = new TickSetting("Only while targeting", false));
      this.registerSetting(e = new TickSetting("Disable while holding S", false));
   }

   @FMLEvent
   public void c(LivingUpdateEvent ev) {
      if (Utils.Player.isPlayerInGame() && mc.thePlayer.maxHurtTime > 0 && mc.thePlayer.hurtTime == mc.thePlayer.maxHurtTime)
      {
         //System.out.println(ev.entity.getName());
         //System.out.println(mc.objectMouseOver.hitInfo);
         if (d.isToggled() && (mc.objectMouseOver == null || mc.objectMouseOver.entityHit == null)) {
            return;
         }

         if (e.isToggled() && Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode())) {
            return;
         }

         if (c.getInput() != 100.0D) {
            double ch = Math.random();
            if (ch >= c.getInput() / 100.0D) {
               return;
            }
         }

         if (a.getInput() != 100.0D) {
            mc.thePlayer.motionX *= a.getInput() / 100.0D;
            mc.thePlayer.motionZ *= a.getInput() / 100.0D;
         }

         if (b.getInput() != 100.0D) {
            mc.thePlayer.motionY *= b.getInput() / 100.0D;
         }
      }

   }
}