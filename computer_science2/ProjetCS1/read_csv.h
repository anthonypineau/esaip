#include <stdio.h> 
#include <string.h>
#include <stdlib.h>

void read_csv(char *filename, char* data[][100]) {
    char buf[1024];
    char token[1024];

    int row_count = 0;
    int field_count = 0;
    int in_double_quotes = 0;
    int token_pos = 0;
    int i = 0;

    FILE *fp = fopen(filename, "r");

    if (!fp) {
        printf("Can't open file\n");
        return;
    }

    while (fgets(buf, 1024, fp)) {
        row_count++;

        if (row_count == 1) {
            continue;
        }

        if (row_count > 100) {
            break;
        }

        field_count = 0;
        i = 0;

        do {
            token[token_pos++] = buf[i];

            if (!in_double_quotes && (buf[i] == ',' || buf[i] == ';' || buf[i] == '\n')) {
                token[token_pos - 1] = 0;
                token_pos = 0;
                data[field_count][row_count - 2] = malloc(sizeof(token));
                strcpy(data[field_count][row_count - 2], token);
                field_count++;
            }

            if (buf[i] == '"' && buf[i + 1] != '"') {
                token_pos--;
                in_double_quotes = !in_double_quotes;
            }

            if (buf[i] == '"' && buf[i + 1] == '"')
                i++;

        } while (buf[++i]);
    }

    fclose(fp);
}
