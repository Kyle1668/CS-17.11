// Kyle O'Brien
// 8-31-2017
// CS 17.11 - Java Programming
// Compiled with the Jetbrains C-Lion IDE on Mac. Tested on Visual Studio Community as well.
// Assignment 0

#include <iostream>
#include <fstream>
#include <string>

void readFromStream(std::ifstream& targetFile);
std::string getFileURL();

// The LetterTree class is implemented as a Binary Search Tree.

// I implemented this program as such so that all comparisons in O(log(n)) time rather than O(n)
// time if I implemented the alphabet as an array. This difference should become apparent as
// the size of the text file increases exponentially.

class LetterTree
{
private:
    struct LetterNode
    {
        char letter;
        long int occurrences = 0;
        LetterNode* leftChild = nullptr;
        LetterNode* rightChild = nullptr;
    };

    LetterNode* rootNode = nullptr;
    int numNodes = 0;

    // Recursive Auxiliary Functions
    void initLetters();
    void printTree(LetterNode* parentNode);
    void addLetter(char newLetter, LetterNode*& parentNode);

public:
    LetterTree();
    void addLetter(char newLetter);
    void printTree();
    int getSize();

};

// Class Methods

LetterTree::LetterTree()
{
    initLetters();
}

void LetterTree::initLetters() {
    for (char letter = 'A'; letter <= 'Z'; letter++) {
        addLetter(letter);
    }
}

void LetterTree::addLetter(char newLetter)
{
    if (isalpha(newLetter))
    {
        newLetter = char(toupper(newLetter));
        if (!rootNode)
        {
            rootNode = new LetterNode;
            rootNode->letter = newLetter;
            numNodes++;
        }
        else if (newLetter == rootNode->letter)
        {
            rootNode->occurrences++;
        }
        else
        {
            addLetter(newLetter, rootNode);
        }
    }
}

void LetterTree::addLetter(char newLetter, LetterNode*& parentNode)
{
    if (!parentNode)
    {
        parentNode = new LetterNode;
        parentNode->letter = newLetter;
        numNodes++;
    }
    else if (newLetter == parentNode->letter)
    {
        parentNode->occurrences++;
    }
    else if (parentNode->letter < newLetter)
    {
        addLetter(newLetter, parentNode->leftChild);
    }
    else
    {
        addLetter(newLetter, parentNode->rightChild);
    }
}

int LetterTree::getSize()
{
    return numNodes;
}

void LetterTree::printTree()
{
    printTree(rootNode);
}

void LetterTree::printTree(LetterNode* parentNode)
{
    if (parentNode != nullptr)
    {
        printTree(parentNode->rightChild);
        std::cout << parentNode->letter << "'s = " << parentNode->occurrences << std::endl;
        printTree(parentNode->leftChild);
    }
}

// Global Methods

std::string getFileURL()
{
    std::cout << "\nEnter URL of the file you wish to read from. : ";
    std::string fileURL;
    std::getline(std::cin, fileURL);
    return fileURL;
}

void readFromStream(std::ifstream& targetFile)
{
    LetterTree alphabet;

    while (!targetFile.eof())
    {
        char output;
        targetFile >> output;
        alphabet.addLetter(output);
    }

    alphabet.printTree();
}

int main()
{
    std::string fileURL = getFileURL();
    std::ifstream targetFile;
    targetFile.open(fileURL);

    if (targetFile.is_open())
    {
        readFromStream(targetFile);
    }
    else
    {
        std::cout << "File not found or error opening file." << std::endl;
    }

    targetFile.close();

    return 0;
}
