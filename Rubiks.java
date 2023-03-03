import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Rubiks {
    
    static String[][]cube = {{
        "r","r","r",
        "r","r","r",
        "r","r","r"
        },{
        "b","b","b",
        "b","b","b",
        "b","b","b"
        },{
        "o","o","o",
        "o","o","o",
        "o","o","o"
        },{
        "g","g","g",
        "g","g","g",
        "g","g","g"
        },{
        "y","y","y",
        "y","y","y",
        "y","y","y"
        },{
        "w","w","w",
        "w","w","w",
        "w","w","w"
        }
    };

    class edgedFace{
        public int current_face;
        int[] edge1 = new int[4]; //index 0 is the face and 1-3 are the elements of the face [1,0,1,2]
        int[] edge2 = new int[4];
        int[] edge3 = new int[4];
        int[] edge4 = new int[4];

        public edgedFace(int face){
            current_face = face;

            switch(face){
                //vertical rotation
                case 0:
                edge1[0] = 1; //blue face
                edge1[1] = 0;
                edge1[2] = 3;
                edge1[3] = 6;

                edge2[0] = 4; //yellow face
                edge2[1] = 0;
                edge2[2] = 3;
                edge2[3] = 6;

                edge3[0] = 3; //green face
                edge3[1] = 0;
                edge3[2] = 3;
                edge3[3] = 6;

                edge4[0] = 5; //white face
                edge4[1] = 0;
                edge4[2] = 3;
                edge4[3] = 6;
                break;

                case 1:
                edge1[0] = 1; //blue face
                edge1[1] = 2;
                edge1[2] = 5;
                edge1[3] = 8;

                edge2[0] = 4; //yellow face
                edge2[1] = 2;
                edge2[2] = 5;
                edge2[3] = 8;

                edge3[0] = 3; //green face
                edge3[1] = 2;
                edge3[2] = 5;
                edge3[3] = 8;

                edge4[0] = 5; //white face
                edge4[1] = 2;
                edge4[2] = 5;
                edge4[3] = 8;
                break;

                //circular rotaion
                case 2:
                edge1[0] = 0; //red face
                edge1[1] = 0;
                edge1[2] = 1;
                edge1[3] = 2;

                edge2[0] = 1; //blue face
                edge2[1] = 0;
                edge2[2] = 1;
                edge2[3] = 2;

                edge3[0] = 2; //orange face
                edge3[1] = 0;
                edge3[2] = 1;
                edge3[3] = 2;

                edge4[0] = 3; //green face
                edge4[1] = 0;
                edge4[2] = 1;
                edge4[3] = 2;
                break;

                case 3:
                edge1[0] = 0; //red face
                edge1[1] = 6;
                edge1[2] = 7;
                edge1[3] = 8;

                edge2[0] = 1; //blue face
                edge2[1] = 6;
                edge2[2] = 7;
                edge2[3] = 8;

                edge3[0] = 2; //orange face
                edge3[1] = 6;
                edge3[2] = 7;
                edge3[3] = 8;

                edge4[0] = 3; //green face
                edge4[1] = 6;
                edge4[2] = 7;
                edge4[3] = 8;
                break;

                //horizontal rotation
                case 4:
                edge1[0] = 0; //red face
                edge1[1] = 0;
                edge1[2] = 1;
                edge1[3] = 2;

                edge2[0] = 4; //yellow face
                edge2[1] = 0;
                edge2[2] = 1;
                edge2[3] = 2;

                edge3[0] = 2; //orange face
                edge3[1] = 0;
                edge3[2] = 1;
                edge3[3] = 2;

                edge4[0] = 5; //white face
                edge4[1] = 6;
                edge4[2] = 7;
                edge4[3] = 8;
                break;

                case 5:
                edge1[0] = 0; //red face
                edge1[1] = 6;
                edge1[2] = 7;
                edge1[3] = 8;

                edge2[0] = 4; //yellow face
                edge2[1] = 6;
                edge2[2] = 7;
                edge2[3] = 8;

                edge3[0] = 2; //orange face
                edge3[1] = 6;
                edge3[2] = 7;
                edge3[3] = 8;

                edge4[0] = 5; //white face
                edge4[1] = 0;
                edge4[2] = 1;
                edge4[3] = 2;
                break;
            }
        }
    }

    public void turnFace(int index, String direction){
        edgedFace eFace = new edgedFace(index);

        String[][] copy = new String[6][9];

        for(int i=0; i<6; i++){
            for(int j=0; j<9; j++){
                copy[i][j]=cube[i][j];
            }
        }

        switch(direction){
            case "clockwise":
                //edge 1 changed
                cube[eFace.edge1[0]][eFace.edge1[1]] = copy[eFace.edge4[0]][eFace.edge4[1]];
                cube[eFace.edge1[0]][eFace.edge1[2]] = copy[eFace.edge4[0]][eFace.edge4[2]];
                cube[eFace.edge1[0]][eFace.edge1[3]] = copy[eFace.edge4[0]][eFace.edge4[3]];
                
                //edge 2 changed
                cube[eFace.edge2[0]][eFace.edge2[1]] = copy[eFace.edge1[0]][eFace.edge1[1]];
                cube[eFace.edge2[0]][eFace.edge2[2]] = copy[eFace.edge1[0]][eFace.edge1[2]];
                cube[eFace.edge2[0]][eFace.edge2[3]] = copy[eFace.edge1[0]][eFace.edge1[3]];

                //edge 3 changed
                cube[eFace.edge3[0]][eFace.edge3[1]] = copy[eFace.edge2[0]][eFace.edge2[1]];
                cube[eFace.edge3[0]][eFace.edge3[2]] = copy[eFace.edge2[0]][eFace.edge2[2]];
                cube[eFace.edge3[0]][eFace.edge3[3]] = copy[eFace.edge2[0]][eFace.edge2[3]];

                //edge 4 changed
                cube[eFace.edge4[0]][eFace.edge4[1]] = copy[eFace.edge3[0]][eFace.edge3[1]];
                cube[eFace.edge4[0]][eFace.edge4[2]] = copy[eFace.edge3[0]][eFace.edge3[2]];
                cube[eFace.edge4[0]][eFace.edge4[3]] = copy[eFace.edge3[0]][eFace.edge3[3]];
            break;
            case "counter_clockwise":
                //edge 1 changed
                cube[eFace.edge1[0]][eFace.edge1[1]] = copy[eFace.edge2[0]][eFace.edge2[1]];
                cube[eFace.edge1[0]][eFace.edge1[2]] = copy[eFace.edge2[0]][eFace.edge2[2]];
                cube[eFace.edge1[0]][eFace.edge1[3]] = copy[eFace.edge2[0]][eFace.edge2[3]];
                
                //edge 2 changed
                cube[eFace.edge2[0]][eFace.edge2[1]] = copy[eFace.edge3[0]][eFace.edge3[1]];
                cube[eFace.edge2[0]][eFace.edge2[2]] = copy[eFace.edge3[0]][eFace.edge3[2]];
                cube[eFace.edge2[0]][eFace.edge2[3]] = copy[eFace.edge3[0]][eFace.edge3[3]];

                //edge 3 changed
                cube[eFace.edge3[0]][eFace.edge3[1]] = copy[eFace.edge4[0]][eFace.edge4[1]];
                cube[eFace.edge3[0]][eFace.edge3[2]] = copy[eFace.edge4[0]][eFace.edge4[2]];
                cube[eFace.edge3[0]][eFace.edge3[3]] = copy[eFace.edge4[0]][eFace.edge4[3]];

                // //edge 4 changed
                cube[eFace.edge4[0]][eFace.edge4[1]] = copy[eFace.edge1[0]][eFace.edge1[1]];
                cube[eFace.edge4[0]][eFace.edge4[2]] = copy[eFace.edge1[0]][eFace.edge1[2]];
                cube[eFace.edge4[0]][eFace.edge4[3]] = copy[eFace.edge1[0]][eFace.edge1[3]];
            break;
        }
    }

    public void showCube(){
        
        int ind = 0;
        for(int x=0; x<6; x++){
            for(int y=0; y<3; y++){
                for(int z=0; z<3; z++){
                    System.out.print(cube[x][ind++]);
                    if (z<2){
                        System.out.print("|");
                    }
                }
                System.out.println();
            }
            ind = 0;
            System.out.println();
        }
    }

    public static void main(String[] args) 
    throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Rubiks RubiksCube = new Rubiks();
        RubiksCube.showCube();

        boolean argsCheck = false;
        int argsRunIndex = 0;

        ArrayList <String> inputSave = new ArrayList<>();

        String[] possibleInputs = {"u","u'","d","d'","r","r'","l","l'","f","f'","b","b'"};

        if(args.length > 0){
            argsCheck = true;
        }

        boolean proceed = true;
        while(proceed){
            String input;

            if(!argsCheck){
                input = reader.readLine();
                if (input.equals("u") ||
                    input.equals("u'")||
                    input.equals("d") ||
                    input.equals("d'")||
                    input.equals("r") ||
                    input.equals("r'")||
                    input.equals("l") ||
                    input.equals("l'")||
                    input.equals("f" )||
                    input.equals("f'")||
                    input.equals("b" )||
                    input.equals("b'")){
                        inputSave.add(input);
                }
            }else{
                if(argsRunIndex == args.length){
                    argsCheck = false;
                    input = "s";
                }else{
                    input = args[argsRunIndex];
                    argsRunIndex++;
                }
            }

            switch(input){
                case "u":
                    RubiksCube.turnFace(2, "clockwise");
                    RubiksCube.showCube();
                break;
                case "d":
                    RubiksCube.turnFace(3, "counter_clockwise");
                    RubiksCube.showCube();
                break;
                case "r":
                    RubiksCube.turnFace(1, "clockwise");
                    RubiksCube.showCube();
                break;
                case "l":
                    RubiksCube.turnFace(0, "counter_clockwise");
                    RubiksCube.showCube();
                break;
                case "f":
                    RubiksCube.turnFace(5, "counter_clockwise");
                    RubiksCube.showCube();
                break;
                case "b":
                    RubiksCube.turnFace(4, "clockwise");
                    RubiksCube.showCube();
                break;


                case "u'":
                    RubiksCube.turnFace(2, "counter_clockwise");
                    RubiksCube.showCube();
                break;
                case "d'":
                    RubiksCube.turnFace(3, "clockwise"); 
                    RubiksCube.showCube();
                break;
                case "r'":
                    RubiksCube.turnFace(1, "counter_clockwise");
                    RubiksCube.showCube();
                break;
                case "l'":
                    RubiksCube.turnFace(0, "clockwise");
                    RubiksCube.showCube();
                break;
                case "f'":
                    RubiksCube.turnFace(5, "clockwise");
                    RubiksCube.showCube();
                break;
                case "b'":
                    RubiksCube.turnFace(4, "counter_clockwise");
                    RubiksCube.showCube();
                break;


                case "s": //solve
                System.out.print("Solution: ");
                for (int inputindex = inputSave.size()-1; inputindex >= 0; inputindex--) {
                    input = inputSave.get(inputindex);
                    if(input.contains("'")){
                        System.out.print(input.charAt(0)+" ");                       
                    } else {System.out.print(input+"'"+" ");
                    }
                } System.out.println();
                break;
                case "n": //randomize
                    for (int randomize = 0; randomize < 5; randomize++){
                        int randint = (int)(Math.random()*12)+0;
                        System.out.print(possibleInputs[randint]+" ");
                    } System.out.println();
                break;
                case "q": //quit
                    System.out.println("quit");
                    System.out.print("Solution: ");
                    for (int inputindex = inputSave.size()-1; inputindex >= 0; inputindex--) {
                        input = inputSave.get(inputindex);
                        if(input.contains("'")){
                            System.out.print(input.charAt(0)+" ");
                        } else {System.out.print(input+"'"+" ");}
                    } System.out.println();
                    proceed = false;
                break;
                default:
                    System.out.println("unknown input");
                break;
            }
        }
    }


}
