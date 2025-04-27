public class SortedList {
    private String[] wordsList;
    private int initSize = 10;
    public int size;

    public SortedList() {
        wordsList = new String[initSize];
        size = 0;
    }

    private void dynamicSize() {
        if(this.isFull()) {
            String[] newWordList = new String[wordsList.length * 2];
            int i = 0;
            for (String word : wordsList){
                newWordList[i] = word;
                i++;
            }
            wordsList = newWordList;
        }
    }

    private int binSearchPoint(String target){
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comp = target.compareTo(wordsList[mid]);

            if (comp == 0){
                return mid;
            } else if (comp < 0){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    //Adds the word to the list
    public void add(String word){
        dynamicSize();
        int searchPoint = binSearchPoint(word);

        for(int i = size; i > searchPoint; i--){
            wordsList[i] = wordsList[i - 1];
        }

        wordsList[searchPoint] = word;
        size++;
    }

    public int findIndex(String target){
        return binSearchPoint(target);
    }

    public String getWord(int target){
        if(target >= 0 && target < size) {
            return wordsList[target];
        }
        return "Error: Index out of bounds.";
    }

    private boolean isFull(){
        return size == wordsList.length;
    }

    @Override
    public String toString(){
        String bigString = "";
        for(int i = 0; i < wordsList.length; i++){
            if(wordsList[i] != null){
                bigString += (i + 1) + ". " + wordsList[i] + "\n";
            }
        }
        return bigString;
    }
}
