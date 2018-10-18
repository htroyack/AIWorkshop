/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package troyack.aiworkshop;

import java.util.ArrayList;

/**
 *
 * @author htroyack
 */
interface Problem {
    public boolean checkSolution(ProblemState state);
    public ArrayList<ProblemState> sucessorFunction(ProblemState state);
    public ProblemState getInitialState();
}
