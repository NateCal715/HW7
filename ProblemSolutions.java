/******************************************************************
 *
 *   NATE CALDERON / SECTION 001
 *
 *
 *   This java file contains the problem solutions for the methods selectionSort,
 *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueCanoes methods.
 *
 ********************************************************************/

import java.util.Arrays;

public class ProblemSolutions {

    /**
     * Method SelectionSort
     *
     * This method performs a selection sort. This file will be spot checked,
     * so ENSURE you are performing a Selection Sort!
     *
     * This is an in-place sorting operation that has two function signatures. This
     * allows the second parameter to be optional, and if not provided, defaults to an
     * ascending sort. If the second parameter is provided and is false, a descending
     * sort is performed.
     *
     * @param values        - int[] array to be sorted.
     * @param ascending     - if true,method performs an ascending sort, else descending.
     *                        There are two method signatures allowing this parameter
     *                        to not be passed and defaulting to 'true (or ascending sort).
     */

    public  void selectionSort(int[] values) {
        selectionSort(values, true);
    }

    public static void selectionSort(int[] values, boolean ascending ) {

        int n = values.length;

        for (int i = 0; i < n - 1; i++) {
            // Repurposed Selection Sort Psuedo Code
            // Initializing Min at beginning
            int min = i;
            // Increment using optional n value
            for (int j = i + 1; j < n; j++) {
                // Find the index of the real min element
                if (ascending) {
                    if (values[j] < values[min]) {
                        min = j;
                    }
                } else {
                    if (values[j] > values[min]) {
                        min = j;
                    }
                }
            }
                // Swap min @ index j and current element i
                int temp = values[min];
                values[min] = values[i];
                values[i] = temp;

        }

    } // End class selectionSort
/*
for i=0 to a.length-1
        min = i
        for j=i+1 to a.length-1
           if a[j] < a[min]
              min = j
           if min != i
              swap a[min] and a[j]
*/

    /**
     *  Method mergeSortDivisibleByKFirst
     *
     *  This method will perform a merge sort algorithm. However, all numbers
     *  that are divisible by the argument 'k', are returned first in the sorted
     *  list. Example:
     *        values = { 10, 3, 25, 8, 6 }, k = 5
     *        Sorted result should be --> { 10, 25, 3, 6, 8 }
     *
     *        values = { 30, 45, 22, 9, 18, 39, 6, 12 }, k = 6
     *        Sorted result should be --> { 30, 18, 6, 12, 9, 22, 39, 45 }
     *
     * As shown above, this is a normal merge sort operation, except for the numbers
     * divisible by 'k' are first in the sequence.
     *
     * @param values    - input array to sort per definition above
     * @param k         - value k, such that all numbers divisible by this value are first
     */

    public void mergeSortDivisibleByKFirst(int[] values, int k) {

        // Protect against bad input values
        if (k == 0)  return;
        if (values.length <= 1)  return;

        mergeSortDivisibleByKFirst(values, k, 0, values.length-1);
    }

    private void mergeSortDivisibleByKFirst(int[] values, int k, int left, int right) {

        if (left >= right)
            return;

        int mid = left + (right - left) / 2;
        mergeSortDivisibleByKFirst(values, k, left, mid);
        mergeSortDivisibleByKFirst(values, k, mid + 1, right);
        mergeDivisbleByKFirst(values, k, left, mid, right);
    }

    /*
     * The merging portion of the merge sort, divisible by k first
     */

    private void mergeDivisbleByKFirst(int arr[], int k, int left, int mid, int right)
    {
       int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, t = 0;
        while (i <= mid) {
            if (arr[i] % k == 0 ) temp[t++] = arr[i];
            i++;
        } 
        while (j <= right) {
            if (arr[j] % k == 0) temp[t++] = arr[j];
            j++;
        }

        while (i <= mid) {
            if (arr[i] % k != 0) temp[t++] = arr[i];
            i++;
        }
        while (j <= right) {
            if (arr[j] != 0) temp[t++] = arr[j];
            j++;
        }

        System.arraycopy(temp, 0, arr, left, temp.length);
    }
        
/* 
MergeSort(array, low, high) {
    If (low < high ) (LEFT >= RIGHT) {
        mid = floor((low + high) / 2); (MID = LEFT + (RIGHT - LEFT) / 2) 
        MergeSort(array, low, mid);
        MergeSort(array, mid+1, high); 
        Merge(array, low, mid, high);
    } 
}
*/
/*   
    private void mergeDivisbleByKFirst(array, low (LEFT), mid, right, int k) {
        n1 = mid (RIGHT) - left + 1;
        n2 = high – mid (mid - RIGHT);
        let L[1,...n1+1] and R[1...n2+1] be new arrays for (i=1 to n1)
        L[i] = array[low + i-1]
        For (j=1 to n2)
        R[j] = array[mid + j] L[n1 + 1] = infinity R[n2 + 1] = infinity i=1
        j=1
        for (k=low to high) {
            if L[i] <= R[j] array[k] = L[i] i=i+1
        else
            array[k] = R[j] j=j+1
        }
    }
*/ 
/* 
Algorithm mergeSort(S, C)
	Input sequence S with n elements, comparator C 
	Output sequence S sorted according to C
if S.size() > 1
	(S1, S2) <-- partition(S, n/2) 
	mergeSort(S1, C)
	mergeSort(S2, C)
	S <-- merge(S1, S2)
*/
/* 
Algorithm merge(A, B)
	Input sequences A and B with 
        n/2 elements each 
	Output sorted sequence of A union B
S <-- empty sequence
while NOTA.isEmpty()  ^ NOTB.isEmpty()
	if A.first().element() < B.first().element()
		S.addLast(A.remove(A.first()))
	else
		S.addLast(B.remove(B.first()))
while NOTA.isEmpty()
    S.addLast(A.remove(A.first()))
while NOTB.isEmpty()
    S.addLast(B.remove(B.first()))
return S
 */



    /**
     * Method asteroidsDestroyed
     *
     * You are given an integer 'mass', which represents the original mass of a planet.
     * You are further given an integer array 'asteroids', where asteroids[i] is the mass
     * of the ith asteroid.
     *
     * You can arrange for the planet to collide with the asteroids in any arbitrary order.
     * If the mass of the planet is greater than or equal to the mass of the asteroid, the
     * asteroid is destroyed and the planet gains the mass of the asteroid. Otherwise, the
     * planet is destroyed.
     *
     * Return true if possible for all asteroids to be destroyed. Otherwise, return false.
     *
     * Example 1:
     *   Input: mass = 10, asteroids = [3,9,19,5,21]
     *   Output: true
     *
     * Explanation: One way to order the asteroids is [9,19,5,3,21]:
     * - The planet collides with the asteroid with a mass of 9. New planet mass: 10 + 9 = 19
     * - The planet collides with the asteroid with a mass of 19. New planet mass: 19 + 19 = 38
     * - The planet collides with the asteroid with a mass of 5. New planet mass: 38 + 5 = 43
     * - The planet collides with the asteroid with a mass of 3. New planet mass: 43 + 3 = 46
     * - The planet collides with the asteroid with a mass of 21. New planet mass: 46 + 21 = 67
     * All asteroids are destroyed.
     *
     * Example 2:
     *   Input: mass = 5, asteroids = [4,9,23,4]
     *   Output: false
     *
     * Explanation:
     * The planet cannot ever gain enough mass to destroy the asteroid with a mass of 23.
     * After the planet destroys the other asteroids, it will have a mass of 5 + 4 + 9 + 4 = 22.
     * This is less than 23, so a collision would not destroy the last asteroid.
     *
     * Constraints:
     *     1 <= mass <= 105
     *     1 <= asteroids.length <= 105
     *     1 <= asteroids[i] <= 105
     *
     * @param mass          - integer value representing the mass of the planet
     * @param asteroids     - integer array of the mass of asteroids
     * @return              - return true if all asteroids destroyed, else false.
     */

    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {
        // Ideas: Sort with smallest first, so you can create the most mass possible
        // Sort the array of asteroids in ascending order
        Arrays.sort(asteroids);
        // Iterate thru asteroids
        for (int asteroid : asteroids) {
            // if currentMass is greater or equal to asteroid
            if (mass >= asteroid) {
                // Planet wins, add asteroid to mass
                mass += asteroid;
            } else {
                // Case where currentMass is not greater or equal to asteroid
                return false; 
            }
        }
        // Planet Wins
        return true;

    }


    /**
     * Method numRescueSleds
     *
     * You are given an array of people where people[i] is the weight of the ith person,
     * and an infinite number of rescue sleds where each sled can carry a maximum weight
     * of limit. Each sled carries at most two people at the same time, provided the
     * sum of the weight of those people is at most limit. Return the minimum number
     * of rescue sleds to carry every given person.
     *
     * Example 1:
     *    Input: people = [1,2], limit = 3
     *    Output: 1
     *    Explanation: 1 sled (1, 2)
     *
     * Example 2:
     *    Input: people = [3,2,2,1], limit = 3
     *    Output: 3
     *    Explanation: 3 sleds (1, 2), (2) and (3)
     *
     * Example 3:
     *    Input: people = [3,5,3,4], limit = 5
     *    Output: 4
     *    Explanation: 4 sleds (3), (3), (4), (5)
     *
     * @param people    - an array of weights for people that need to go in a sled
     * @param limit     - the weight limit per sled
     * @return          - the minimum number of rescue sleds required to hold all people
     */

    public static int numRescueSleds(int[] people, int limit) {
        // Ideas: sort in ascending order, 
        // Identify if i + (i++) >= limit
        
        // Sort people in ascending order
        Arrays.sort(people);
        // Pointer Usage
        int i = 0;
        int j = people.length - 1;
        int sleds = 0;
        // 
        while (i <= j) {
            if (people[i] + people[j] <= limit) {
                i++;
            }
            // Decrement and increment pointers
            j--;
            sleds++;
        }
        return sleds++;
    }

} // End Class ProblemSolutions

