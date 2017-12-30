package com.versionpb.briskybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.versionpb.briskybird.BriskyDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = BriskyDemo.WIDTH ;
		config.height = BriskyDemo.HEIGHT;
		config.title = BriskyDemo.TITLE;
		new LwjglApplication(new BriskyDemo(), config);
	}
}
