/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package troyack.aiworkshop;

/**
 *
 * @author htroyack
 */
interface ProblemState {
    public SolutionTreeNode solve(ProblemState initialState);
}
