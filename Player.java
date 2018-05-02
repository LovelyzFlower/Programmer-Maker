package PM_game;

public class Player {
	String name = "";
	int money = 200000; //용돈
	int hp = 100; // 체력
	int stress_num = 5; // 스트레스
	int study_num = 5; // 공부력
	int game_num = 5; // 게임력
	int hobby_num = 5; // 취미력
	int goodwill_num = 5; // 호감도
	Player(String text){
		this.name=text;
	}
}
