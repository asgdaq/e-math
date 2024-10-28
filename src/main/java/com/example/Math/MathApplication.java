package com.example.Math;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

@SpringBootApplication
@Controller
public class MathApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathApplication.class, args);
	}

	@GetMapping("")
	public String home(){
		return "Home.html.html";
	}

	@GetMapping("Adunare.html")
	public String Adunare(){
		return "Adunare.html";
	}

	@GetMapping("Inmultirea.html")
	public String Inmultirea(){
		return "Inmultire.html";
	}

	@GetMapping("Procente.html")
	public String Procente(){
		return "Procente.html";
	}

	int BigMaxAdunare = 0;
	String BigSing ="";
	int Random_1 = 0;
	int Random_2 = 0;

	ArrayList<String> list = new ArrayList<>();

	boolean random_adunare = false;
	boolean random_inmultire = false;
	boolean difference_percentage = false;

	@PostMapping("/Go")
	public String Go(@RequestParam("number") int number, @RequestParam("Operation") String operation
							,Model model){
		BigSing = operation;

		if (operation.equals("on") || random_adunare){
			String[] symbols = {"+", "-"};
			random_adunare = true;

			// Generate a random index (0 or 1)
			int randomIndex = new Random().nextInt(2);

			// Select a random symbol
			String result = symbols[randomIndex];
			BigSing = result;
		}

		BigMaxAdunare = number;

		System.out.println(BigSing);

		int random_1  = (int) ((Math.random() * (number - 1)) + 1);
		int random_2  = (int) ((Math.random() * (number - 1)) + 1);

		if( BigSing.equals("-") && random_1 < random_2){
			int temp = random_2;
			random_2 = random_1;
			random_1 = temp;
		}
		else if(BigSing.equals("/*/")|| random_inmultire){
			String[] symbols = {"*", "/"};
			random_inmultire = true;

			// Generate a random index (0 or 1)
			int randomIndex = new Random().nextInt(2);

			// Select a random symbol
			String result = symbols[randomIndex];
			BigSing = result;
			if (BigSing.equals("/")) {
				while ((random_1 % random_2) != 0 || (random_1 / random_2) == 1 || random_2 == 1) {
					random_1 = (int) ((Math.random() * (number - 1)) + 1);
					random_2 = (int) ((Math.random() * (number - 1)) + 1);
				}
				System.out.println("**************************************************");
				System.out.println(random_1 + "/" + random_2);
				System.out.println("**************************************************");
			}
		}

		else if(BigSing.equals("/%") || difference_percentage){
			difference_percentage = true;
			BigSing = "% of ";
		}

		else if (BigSing.equals("/")) {
			// Ensure random_1 and random_2 meet the criteria
			while ((random_1 % random_2) != 0 || (random_1 / random_2) == 1 || random_2 == 1) {
				random_1 = (int) ((Math.random() * (number - 1)) + 1);
				random_2 = (int) ((Math.random() * (number - 1)) + 1);
			}
			System.out.println("**************************************************");
			System.out.println(random_1 + "/" + random_2);
			System.out.println("**************************************************");

		}
		else if (BigSing.equals("%") || BigSing.equals("% of ")){
			while ((random_1 * random_2) % 100 != 0) {
				random_1 = (int) ((Math.random() * (number - 1)) + 1);
				random_2 = (int) ((Math.random() * (number - 1)) + 1);
				System.out.println("-----------------------******************-------------------");
				System.out.println("Incercare noua:\n" + random_1 + " % " + random_2);
			}

			BigSing= "% of ";
		}

		Random_1 = random_1;
		Random_2 = random_2;

		System.out.println(random_1 + " -> " + random_2);

		model.addAttribute("Random_1" , Random_1);
		model.addAttribute("Random_2" , Random_2);
		model.addAttribute("Operation" , BigSing);

		return "Game.html";
	}
	@PostMapping("/GoMultiplication")
	public String GoMultiplication(@RequestParam("number") int number,
								   @RequestParam("OperationMult") String OperationMult, Model model){
		BigSing = OperationMult;
		System.out.println(BigSing);

		BigMaxAdunare = number;

		int random_1  = (int) ((Math.random() * (number - 1)) + 1);
		int random_2 = (int) ((Math.random() * (number - 1)) + 1);
		if (BigSing.equals("/")) {
			// Ensure random_1 and random_2 meet the criteria
			while ((random_1 % random_2) != 0 || (random_1 / random_2) == 1) {
				random_1 = (int) ((Math.random() * (number - 1)) + 1);
				random_2 = (int) ((Math.random() * (number - 1)) + 1);
			}
			System.out.println("**************************************************");
			System.out.println(random_1 + "/" + random_2);
			System.out.println("**************************************************");
		}
		else if(BigSing.equals("/*/")||random_inmultire){
			String[] symbols = {"*", "/"};

			// Generate a random index (0 or 1)
			int randomIndex = new Random().nextInt(2);

			// Select a random symbol
			String result = symbols[randomIndex];
			BigSing = result;
			random_inmultire = true;

		}



		Random_1 = random_1;
		Random_2 = random_2;

		model.addAttribute("Random_1", Random_1);
		model.addAttribute("Random_2", Random_2);
		model.addAttribute("Operation", BigSing);

		return "Game.html";
	}

	@PostMapping("/Answer")
	public String answer(@RequestParam("answer") int answer, @RequestParam("time") String time,
						 Model model){
		String FinalAnswer = "";
		String bifa = "";
		String wrongAnswer = "";

		// Handling the division case
		if (BigSing.equals("/")) {
			// Ensure random_1 and random_2 meet the criteria
			while ((Random_1 % Random_2) != 0 || (Random_1 / Random_2) == 1) {
				Random_1 = (int) ((Math.random() * (BigMaxAdunare - 1)) + 1);
				Random_2 = (int) ((Math.random() * (BigMaxAdunare - 1)) + 1);
			}
			System.out.println("**************************************************");
			System.out.println(Random_1 + "/" + Random_2);
			System.out.println("**************************************************");
		}
		switch (BigSing) {
			case "+":
				if ((Random_1 + Random_2) != answer) {
					bifa = "✘";
					FinalAnswer = String.valueOf(Random_1 + Random_2);
					wrongAnswer = "/(" + answer + ")";
				} else {
					bifa = "✔";
					FinalAnswer = String.valueOf(Random_1 + Random_2);
				}
				break;
			case "-":
				if ((Random_1 - Random_2) != answer) {
					bifa = "✘";
					FinalAnswer = String.valueOf(Random_1 - Random_2);
					wrongAnswer = "/(" + answer + ")";
				} else {
					bifa = "✔";
					FinalAnswer = String.valueOf(Random_1 - Random_2);
				}
				break;
			case "*":
				if ((Random_1 * Random_2) != answer) {
					bifa = "✘";
					FinalAnswer = String.valueOf(Random_1 * Random_2);
					wrongAnswer = "/(" + answer + ")";
				} else {
					bifa = "✔";
					FinalAnswer = String.valueOf(Random_1 * Random_2);
				}
				break;
			case "/":
				if ((Random_1 / Random_2) != answer) {
					bifa = "✘";
					FinalAnswer = String.valueOf(Random_1 / Random_2);
					wrongAnswer = "/(" + answer + ")";
				} else {
					bifa = "✔";
					FinalAnswer = String.valueOf(Random_1 / Random_2);
				}
				break;

			case "% of ":

				if (((Random_1*Random_2)/100) != answer){
					bifa = "✘";
					FinalAnswer = String.valueOf((Random_1*Random_2)/100) + "%";
					wrongAnswer = "/(" + answer + "% )";
				}
				else{
					bifa = "✔";
					FinalAnswer = String.valueOf((Random_1*Random_2)/100) + "%";
				}

				break;
		}

		String Question = bifa + "\t(" + time + ")\t" + Random_1 + " " +
				BigSing + " " + Random_2 + " = " + FinalAnswer + wrongAnswer;
		list.add(Question);
		System.out.println(Question);
		model.addAttribute("list", list);

		return Go(BigMaxAdunare, BigSing, model);
	}


	@PostMapping("/GoPercentage")
	public String GoPercentage(@RequestParam("number") int number,
							   Model model){

		/// random_1 -> numarul total
		/// random_2 -> procentul care trebuie aflat

		BigSing = "%";

		int random_1  = (int) ((Math.random() * (number - 1)) + 1);
		int random_2 = (int) ((Math.random() * (number - 1)) + 1);


			while ((random_1 * random_2) % 100 != 0) {
				random_1 = (int) ((Math.random() * (number - 1)) + 1);
				random_2 = (int) ((Math.random() * (number - 1)) + 1);
				System.out.println("-----------------------******************-------------------");
				System.out.println("Incercare noua:\n" + random_1 + " % " + random_2);
			}
			BigSing = "%";


		System.out.println(random_1 +" of " + random_2);
		Random_1 = random_1;
		Random_2 = random_2;

		model.addAttribute("Random_1" , Random_1);
		model.addAttribute("Random_2" , Random_2);
		model.addAttribute("Operation" , BigSing);
		return Go(number, BigSing, model);

	}

	@PostMapping("/Back")
	public String back(){
		random_adunare = false;
		random_inmultire = false;
		difference_percentage = false;
		list.clear();
		return home();
	}




}
