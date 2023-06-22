import java.sql.Time;

public class workingTime {    
	public static void main(String[] args) {

		long oneHourByMilliSec = 1000 * 60 * 60;  // 1時間をミリ秒換算
		long oneMinByMilliSec  = 1000 * 60;       // 1分をミリ秒換算
		int  oneHourByMin      = 60;              // 1時間を分換算

		Time startTime  = Time.valueOf(args[0]);
		Time finishTime = Time.valueOf(args[1]);

		long workingTime = finishTime.getTime() - startTime.getTime();

		int workingHour = (int)(workingTime / oneHourByMilliSec);                 // 時間に換算
		int workingMin  = (int)(workingTime / oneMinByMilliSec) % oneHourByMin;  // 分に換算

		System.out.println("本日の労働時間は" + workingHour + "時間" + workingMin + "分です。");


		// 稼働時間8時間を超えた場合
		if(workingTime > 8*oneHourByMilliSec) {     
			workingTime  -=oneHourByMilliSec;

			// 稼働時間6時間超え〜8時間以下
		}else if((workingTime > 6*oneHourByMilliSec) && (workingTime <= 8*oneHourByMilliSec)) {
			workingTime  -=45*oneMinByMilliSec;
		}

		workingHour = (int)(workingTime / oneHourByMilliSec);                 // 時間に換算
		workingMin  = (int)(workingTime / oneMinByMilliSec) % oneHourByMin;   // 分に換算

		System.out.println("本日の実労働時間は" + workingHour + "時間" + workingMin + "分です。");


		double  hourlyWage  = 900;                                              // 時給
		double  overtimePay = hourlyWage * 1.25;                                // 残業手当

		if(workingTime <= 8 * oneHourByMilliSec)  {                             // 8H以下
			hourlyWage =  hourlyWage * ( workingHour + workingMin / 60.0);    
		}else{                                                                  // 残業有り
			hourlyWage = 8 * hourlyWage;                                        // 8時間分の賃金

			workingTime -= 8 * oneHourByMilliSec;                               // 残業時間

			workingHour = (int)(workingTime / oneHourByMilliSec);                 // 時間に換算
			workingMin  = (int)(workingTime / oneMinByMilliSec) % oneHourByMin;   // 分に換算

			// 8時間分の賃金に（残業時間 * 1.25倍の時給）を加算
			hourlyWage +=  (int)(overtimePay * ( workingHour + workingMin / 60.0)); 
		}
		System.out.println("hourlyWage = " + hourlyWage + "円");


	}
}