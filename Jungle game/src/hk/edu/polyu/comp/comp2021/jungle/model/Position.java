package hk.edu.polyu.comp.comp2021.jungle.model;

// Position of a piece in a board
public enum Position {
    A1(0,0), A2(0,1), A3(0,2), A4(0,3), A5(0,4), A6(0,5), A7(0,6), A8(0,7), A9(0,8),
    B1(1,0), B2(1,1), B3(1,2), B4(1,3), B5(1,4), B6(1,5), B7(1,6), B8(1,7), B9(1,8),
    C1(2,0), C2(2,1), C3(2,2), C4(2,3), C5(2,4), C6(2,5), C7(2,6), C8(2,7), C9(2,8),
    D1(3,0), D2(3,1), D3(3,2), D4(3,3), D5(3,4), D6(3,5), D7(3,6), D8(3,7), D9(3,8),
    E1(4,0), E2(4,1), E3(4,2), E4(4,3), E5(4,4), E6(4,5), E7(4,6), E8(4,7), E9(4,8),
    F1(5,0), F2(5,1), F3(5,2), F4(5,3), F5(5,4), F6(5,5), F7(5,6), F8(5,7), F9(5,8),
    G1(6,0), G2(6,1), G3(6,2), G4(6,3), G5(6,4), G6(6,5), G7(6,6), G8(6,7), G9(6,8);

    // x = column (A - G)
    public int x;

    // y = row (1 - 9)
    public int y;

    Position (int x, int y) {
        this.x = x;
        this.y = y;
    }
}

