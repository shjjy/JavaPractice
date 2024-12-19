package ds.hw5;

public class SparseMatrix {
    int Rows, Columns, Terms;
    MatrixTerm[] matrix;

    public static void main(String[] args) {
        MatrixTerm[] term = initTerms();
        SparseMatrix matrix = new SparseMatrix(5, 6, 6, term);
        printMatrix(matrix);
        printMatrix(Transpose(matrix));
    }

    public SparseMatrix(int rows, int columns, int terms, MatrixTerm[] matrix) {
        this.Rows = rows;
        this.Columns = columns;
        this.Terms = terms;
        this.matrix = matrix;
    }

    private static MatrixTerm[] initTerms() {
        MatrixTerm[] matrixTerms = new MatrixTerm[6];
        matrixTerms[0] = new MatrixTerm(1, 3, 3);
        matrixTerms[1] = new MatrixTerm(1, 5, 4);
        matrixTerms[2] = new MatrixTerm(2, 3, 5);
        matrixTerms[3] = new MatrixTerm(2, 4, 7);
        matrixTerms[4] = new MatrixTerm(4, 2, 2);
        matrixTerms[5] = new MatrixTerm(4, 3, 6);
        return matrixTerms;
    }

    private static void printMatrix(SparseMatrix matrix) {
        for(int i = 0; i < matrix.Rows; i++){
            for(int j = 0; j < matrix.Columns; j++){
                if(!printInMatrix(matrix, i, j)){
                    System.out.printf("%3d", 0);
                }
            }
            System.out.println();
        }
    }

    private static boolean printInMatrix(SparseMatrix matrix, int row, int column) {
        for(MatrixTerm term : matrix.matrix){
            if(term.row == row && term.column == column){
                System.out.printf("%3d", term.value);
                return true;
            }
        }
        return false;
    }

    private static SparseMatrix Transpose(SparseMatrix matrix) {
        SparseMatrix transMatrix = new SparseMatrix(matrix.Columns, matrix.Rows, matrix.Terms, new MatrixTerm[matrix.Terms]);
        for(int i = 0; i < matrix.Terms; i++){
            transMatrix.matrix[i] = new MatrixTerm(matrix.matrix[i].column, matrix.matrix[i].row, matrix.matrix[i].value);
        }
        return transMatrix;
    }
}

class MatrixTerm{
    int row, column, value;

    public MatrixTerm(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }
}

