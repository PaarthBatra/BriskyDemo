package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.versionpb.briskybird.BriskyDemo;

import Sprites.Bird;
import Sprites.Tube;

public class PlayState extends State {
	private Bird bird;		//add this line
	private Texture background;  
	private static final int TUBE_COUNT = 4;
	private static final int TUBE_SPACING = 125;
	private Array<Tube> tubes;

	private Texture ground;
	private Vector2 groundPos1;
	private Vector2 groundPos2;
	private static final int GROUND_OFFSET = -30;
	
	private Sound die;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		cam.setToOrtho(false, BriskyDemo.WIDTH / 2, BriskyDemo.HEIGHT / 2);
		bird = new Bird(50,300);

		background = new Texture("playBg_menu.png");

		tubes = new Array<Tube>();
		for (int i = 1; i <= TUBE_COUNT; i++) { // for loop for adding tubes to the array
			tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));

		}

		ground = new Texture("base.png");
		groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_OFFSET);
		groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_OFFSET);
		
		die = Gdx.audio.newSound(Gdx.files.internal("die.ogg"));
	}

	@Override
	public void handleInput() {
		if(Gdx.input.justTouched())
			bird.jump();


	}

	@Override
	public void update(float dt) {

		handleInput();
		updateGround();
		bird.update(dt);
		cam.position.x = bird.getPosition().x + 80;


		for (Tube tube : tubes){
			if (cam.position.x - (cam.viewportWidth / 2 ) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
				tube.reposition(tube.getPosTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
			}
		}

		if (cam.position.x > 320) {

			for (int i = 0; i < TUBE_COUNT; i++) {
				if (tubes.get(i).collides(bird.getBounds())) {
					die.play(0.3f);
					
					gsm.set(new MenuState(gsm));
					dispose();
				}
			}

		}


		if (bird.getPosition().y <= ground.getHeight() + GROUND_OFFSET) {
			die.play(0.3f);
			
			
			
			gsm.set(new MenuState(gsm));
			dispose();
		}




		cam.update();

	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin(); 
		//sb.draw(background, 0, 0, BriskyDemo.WIDTH, BriskyDemo.HEIGHT);
		sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0, BriskyDemo.WIDTH, BriskyDemo.HEIGHT);
		//sb.draw(bird,50,50);
		sb.draw(bird.getTexture(), bird.getPosition().x,bird.getPosition().y);

		for (Tube tube : tubes) {
			if (tube.getPosTopTube().x > 320) {
				sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
				sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
			}

		}

		sb.draw(ground, groundPos1.x, groundPos1.y);
		sb.draw(ground, groundPos2.x, groundPos2.y);

		sb.end(); 

	}

	@Override
	public void dispose() {
		background.dispose();
		ground.dispose();
		bird.dispose();
		//die.dispose();
		for (Tube tube : tubes) {
			tube.dispose();
		}
		System.out.println("Play State Disposed");	
	}

	private void updateGround() {
		if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth()) {
			groundPos1.add(ground.getWidth() * 2, 0);
		}
		if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
			groundPos2.add(ground.getWidth() * 2, 0);


	}

}
