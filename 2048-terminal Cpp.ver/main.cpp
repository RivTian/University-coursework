#include <cstdio>
#include <iostream>

#include <cstring>
#include <cstdlib>
#include <ctime>

#include <fstream>
#include <vector>
#include <string>
#include <algorithm>

#include "color.h"

int mat[4][4];
int score, maxScore;

void printLine() {
    for (int i = 0; i < 4; i++) printf(C_S_YELLOW "+-----" C_NONE);
    puts(C_S_YELLOW "+" C_NONE);
}

void printLine(int *line) {
    for (int i = 0; i < 4; i++) {
        if (line[i]) {
            printf(C_S_YELLOW "|" C_NONE "%5d", 1 << line[i]);
        } else {
            printf(C_S_YELLOW "|     " C_NONE);
        }
    }
    puts(C_S_YELLOW "|" C_NONE);
}

void printMat() {
    system("clear");

    printLine();
    for (int i = 0; i < 4; i++) {
        printLine(mat[i]);
        printLine();
    }

    printf(C_S_RED "Score = %d, Max score = %d\n" C_NONE, score, maxScore);
}

std::pair<int, int> getRandomCoordinate() {
    static std::vector<std::pair<int, int> > vacancy;
    vacancy.clear();

    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            if (!mat[i][j]) {
                vacancy.push_back(std::make_pair(i, j));
            }
        }
    }

    int index = rand() % vacancy.size();
    return vacancy[index];
}

void addNewNumber() {
    std::pair<int, int> coordinate = getRandomCoordinate();

    const static double RATIO_1 = 0.8;

    double ratio = (double) rand() / RAND_MAX;

    mat[coordinate.first][coordinate.second] = ratio <= RATIO_1 ? 1 : 2;
}

enum Direction {LEFT, RIGHT, UP, DOWN};

bool check(int index, Direction direction) {
    int last = -1;

    if (direction == LEFT) {
        for (int i = 0; i < 4; i++) {
            if (!last && mat[index][i]) return true;
            if (last && mat[index][i] == last) return true;
            last = mat[index][i];
        }
    } else if (direction == RIGHT) {
        for (int i = 3; ~i; i--) {
            if (!last && mat[index][i]) return true;
            if (last && mat[index][i] == last) return true;
            last = mat[index][i];
        }
    } else if (direction == UP) {
        for (int i = 0; i < 4; i++) {
            if (!last && mat[i][index]) return true;
            if (last && mat[i][index] == last) return true;
            last = mat[i][index];
        }
    } else { // else if (direction == DOWN)
        for (int i = 3; ~i; i--) {
            if (!last && mat[i][index]) return true;
            if (last && mat[i][index] == last) return true;
            last = mat[i][index];
        }
    }

    return false;
}

void move(int index, Direction direction) {
    int p, last = -1;

    if (direction == LEFT) {
        p = 0;
        for (int i = 0; i < 4; i++) {
            if (!mat[index][i]) continue;

            if (last == mat[index][i]) {
                mat[index][p - 1] = last + 1;
                score += 1 << (last + 1);
                last = -1;
            } else
                last = mat[index][p++] = mat[index][i];
        }

        while (p < 4) mat[index][p++] = 0;
    } else if (direction == RIGHT) {
        p = 3;
        for (int i = 3; ~i; i--) {
            if (!mat[index][i]) continue;

            if (last == mat[index][i]) {
                mat[index][p + 1] = last + 1;
                score += 1 << (last + 1);
                last = -1;
            } else
                last = mat[index][p--] = mat[index][i];
        }

        while (~p) mat[index][p--] = 0;
    } else if (direction == UP) {
        p = 0;
        for (int i = 0; i < 4; i++) {
            if (!mat[i][index]) continue;

            if (last == mat[i][index]) {
                mat[p - 1][index] = last + 1;
                score += 1 << (last + 1);
                last = -1;
            } else
                last = mat[p++][index] = mat[i][index];
        }

        while (p < 4) mat[p++][index] = 0;
    } else { // else if (direction == DOWN)
        p = 3;
        for (int i = 3; ~i; i--) {
            if (!mat[i][index]) continue;

            if (last == mat[i][index]) {
                mat[p + 1][index] = last + 1;
                score += 1 << (last + 1);
                last = -1;
            } else
                last = mat[p--][index] = mat[i][index];
        }

        while (~p) mat[p--][index] = 0;
    }
}

void move(Direction direction) {
    for (int i = 0; i < 4; i++) move(i, direction);
}

int getCommand() {
    puts("\'Q \'for quiting, \'S\' for restart, \'L\', \'R\', \'U\', \'D\' for moving(Lower case alpha is also ok).");

    std::string line;
    std::getline(std::cin, line);

    if (line.length() != 1) return -1;

    int ret = -1;

    if (line[0] == 'Q' || line[0] == 'q') ret = 4;
    else if (line[0] == 'S' || line[0] == 's') ret = 5;
    else if (line[0] == 'L' || line[0] == 'l') ret = 0;
    else if (line[0] == 'R' || line[0] == 'r') ret = 1;
    else if (line[0] == 'U' || line[0] == 'u') ret = 2;
    else if (line[0] == 'D' || line[0] == 'd') ret = 3;

    if (0 <= ret && ret < 4) {
        int temp = ret;
        ret = -2;

        for (int i = 0; i < 4; i++) {
            if (check(i, (Direction) temp)) {
                ret = temp;
                break;
            }
        }
    }

    return ret;
}

int getCommandForRestart() {
    puts("\'Q\' for quit, \'S\' for restart(Lower case alpha is also ok).");

    std::string line;
    std::getline(std::cin, line);

    if (line.length() != 1) return -1;

    if (line[0] == 'Q' || line[0] == 'q') return 4;
    else if (line[0] == 'S' || line[0] == 's') return 5;

    return -1;
}

bool check() {
    bool isOver = true;

    for (int i = 0; i < 4; i++) {
        for (int d = 0; d < 4; d++) {
            if (check(i, (Direction) d)) {
                isOver = false;
                break;
            }
        }
    }

    return isOver;
}

const char *MAX_SCORE_STORE_FILE = ".game2048TerMaxScore";

void readMax() {
    maxScore = 0;
    std::ifstream fin;
    fin.open(MAX_SCORE_STORE_FILE);
    if (fin) fin >> maxScore;
    fin.close();
}

void saveMax() {
    std::ofstream fout;
    fout.open(MAX_SCORE_STORE_FILE);
    fout << maxScore;
    fout.close();
}

void init() {
    srand(time(NULL));

    memset(mat, 0, sizeof (mat));
    score = 0;

    addNewNumber();
    addNewNumber();
}

int main() {
    readMax();

    while (true) {
        init();
        printMat();

        int flag = 0; //1 for 'quit', 2 for 'restart', 3 for 'game over'
        while (true) {
            int cmd;
            while (true) {
                cmd = getCommand();

                if (cmd == -1) puts("Unkown command.");
                else if (cmd == -2) puts("Can't move in this direction.");
                else break;
            }

            if (cmd == 4) {
                flag = 1;
                break;
            }

            if (cmd == 5) {
                flag = 2;
                break;
            }

            move((Direction) cmd);
            if (score > maxScore) maxScore = score;
            addNewNumber();
            printMat();

            if (check()) {
                flag = 3;
                break;
            }
        }

        if (flag == 1) {
            puts(C_S_BLUE "Quit the game." C_NONE);
            saveMax();
            break;
        }
        if (flag == 2) {
            puts(C_S_GREEN "Restart the game in 3 seconds." C_NONE);
            system("sleep 3");
            continue;
        }
        if (flag == 3) {
            printf(C_S_CYAN "Game over." C_NONE "\nYour score is " C_S_RED "%d" C_NONE "\n", score);

            int cmd;
            while (true) {
                cmd = getCommand();

                if (cmd == -1) puts("Unkown command.");
                else break;
            }

            if (cmd == 4) {
                saveMax();
                break;
            } else continue; //else if (cmd == 5)
        }
    }
    return 0;
}
