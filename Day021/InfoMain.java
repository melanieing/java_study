package fileInfo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InfoMain {
    public static void main(String[] args) {
	
        Scanner sc = new Scanner(System.in);
        InfoManager manager = new InfoManager();
        InfoFileManager filer = null;
        
        try {    
        	while (true) {
	            InfoManager.showMenu();
	            System.out.print("원하시는 메뉴의 번호를 입력하세요 : ");
	            int menu = sc.nextInt();
	            switch (menu) {
	            case 0 : // 프로그램 종료
	                return;
	            case 1 : // 회원 정보 등록
	                System.out.print("등록할 회원의 수를 입력하세요 : ");
	                int infoNum = sc.nextInt();
	                for (int i = 0; i < infoNum; i++) {
	                    System.out.print("회원의 등록번호, 이름, 나이를 차례로 입력하세요 : ");
	                    manager.joinInfo(sc.nextInt(), sc.next(), sc.nextInt());
	                }
	                System.out.println("회원 정보 등록이 완료되었습니다!");
	                break;
	            case 2 : // 회원 정보 목록 확인
	                manager.showInfo();
	                break;
	            case 3 : // 회원 정보 검색
	                System.out.print("어떤 키워드로 검색하시겠습니까? (등록번호(1), 이름(2), 나이(3) 중 번호로 선택) : ");
	                int clue = sc.nextInt();
	                if (clue == 1) {
	                    System.out.print("찾으시려는 회원의 등록번호를 입력하세요 : ");
	                    int clueId = sc.nextInt();
	                    manager.printInfo(clueId);
	                } else if (clue == 2) {
	                    System.out.print("찾으시려는 회원의 이름을 입력하세요 : ");
	                    String clueName = sc.next();
	                    int nameAnswer = manager.searchByName(clueName);
	                    if (nameAnswer == -1) {
	                    	System.out.println("해당 이름을 가진 회원은 없습니다!");
	                    	break;
	                    }
	                    manager.printInfo(nameAnswer);
	                } else if (clue == 3) {
	                    System.out.print("찾으시려는 회원의 나이를 입력하세요 : ");
	                    int clueAge = sc.nextInt();
	                    int ageAnswer = manager.searchByAge(clueAge);
	                    if (ageAnswer == -1) {
	                    	System.out.println("해당 나이를 가진 회원은 없습니다!");
	                    	break;
	                    }
	                    manager.printInfo(ageAnswer);
	                } else {
	                    System.out.println("1, 2, 3 중에서 입력하세요!");
	                }
	                break;
	            case 4 : // 회원 정보 수정
	                System.out.print("정보를 수정할 회원의 등록번호를 입력하세요 : ");
	                int targetId = sc.nextInt();
	                if (manager.searchById(targetId) == null) {
	                	System.out.println("해당 등록번호를 가진 회원은 없습니다!");
	                	continue;
	                }
	            	System.out.println("새로운 정보를 이름, 나이 순으로 입력하세요 : ");
	            	manager.modifyInfo(targetId, sc.next(), sc.nextInt());
	                System.out.println("학생 정보 수정을 완료했습니다!");
	            	break;
	            case 5 : // 회원 정보 삭제
	                System.out.print("삭제할 회원의 등록번호를 입력하세요 : ");
	                int targetId2 = sc.nextInt();
	                if (!manager.deleteInfo(targetId2)) {
	                	System.out.println("해당 등록번호를 가진 회원은 없습니다!");
	                } else {
	                	System.out.println("해당 회원 정보를 삭제했습니다!");
	                }
	                break;
	            case 6 :
	            	System.out.print("어떤 이름의 파일로 저장하시겠습니까? : ");
	            	String fileName = sc.next();
	            	filer = new InfoFileManager(fileName);
	            	filer.makeFile(manager.getInfos());
	            	System.out.printf("\'%s\'로 파일이 저장되었습니다!\n", fileName);
	            	System.out.print("해당 파일의 내용을 확인하시겠습니까? (y/n)");
	            	char answer = sc.next().charAt(0);
	            	if (answer == 'y' || answer == 'Y') {
	            		filer.readFile(filer.getInfoFile());
	            	} else if (answer == 'n' || answer == 'N') {
	            		continue;
	            	} else {
	            		System.out.println("y와 n 중에서 입력하세요!");
	            	}
	            	break;
	            default :
	            	System.out.println("0 ~ 6 사이의 숫자를 입력하세요!");
	            }
	        }
    	} catch(InputMismatchException e) {
        	System.out.println("0 ~ 5 사이의 숫자를 입력하세요!");
        }
        
    }
}