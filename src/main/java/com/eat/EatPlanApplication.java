package com.eat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eat.utils.JwtUtil;

@SpringBootApplication
public class EatPlanApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EatPlanApplication.class, args);
		System.out.println();
	    System.out.println("//                   _ooOoo_");
        System.out.println("//                  o8888888o");
        System.out.println("//                   88\" . \"88");
        System.out.println("//                    (| -_- |)");
        System.out.println("//                   O\\  =  /O");
        System.out.println("//               ____/`---'\\____");
        System.out.println("//               .'  \\\\|     |//  `.");
        System.out.println("//              /  \\\\|||  :  |||//  \\");
        System.out.println("//             /  _||||| -:- |||||-  \\");
        System.out.println("//             |   | \\\\  -  /// |   |");
        System.out.println("//             | \\_|  ''\\---/''  |   |");
        System.out.println("//             \\  .-\\__  `-`  ___/-. /");
        System.out.println("//         ___`. .'  /--.--\\  `. . __");
        System.out.println("//      .\"\" '<  `.___\\_<|>_/___.'  >'\"\".");
        System.out.println("//     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |");
        System.out.println("//     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /");
        System.out.println("//======`-.____`-.___\\_____/___.-`____.-'======");
        System.out.println("//                   `=---='");
        System.out.println("//");
        System.out.println("//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("//          佛祖保佑           永无BUG");
        System.out.println("//         God Bless        Never Crash");
		
		// pri_key가 없을 경우 생성
		if(JwtUtil.getPri_key()==null) {
			JwtUtil.setPri_key();
		}
	}

}
