public static void main(String[] args) {
    // TODO Auto-generated method stub
    Scanner in = new Scanner(System.in);
    seedData();
    int menu;
    for(;;) {
        mainMenu();
        System.out.print("pilihan :");
        menu = in.nextInt();
        in.nextLine();
        switch(menu) {
            case 1 :
                showData();
                System.out.print("Press enter to continue");
                in.nextLine();
                break;
            case 2 :
                filterData("IF");
                break;
            case 3 :
                filterData("CE");
                break;
            case 4 :
                filterData("UM");
                break;
            default:
                System.out.println("Input Tidak Valid");
        }
    }
}
