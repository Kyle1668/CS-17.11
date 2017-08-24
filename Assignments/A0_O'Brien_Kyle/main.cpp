#include <iostream>
#include <fstream>
#include <string>

void readFromStream(std::ifstream& targetFile);
std::string getFileURL();

// The LetterTree class is implemented as a Binary Search Tree.

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

    LetterNode* rootNode;
    int numNodes;

    // Recursive Auxiliary Functions
    void printTree(LetterNode* parentNode);
    void addLetter(char newLetter, LetterNode*& parentNode);

public:
    LetterTree();
    void addLetter(char newLetter);
    void printTree();
    int getSize();

};

LetterTree::LetterTree()
{
    rootNode = nullptr;
    numNodes = 0;
}

// Class Methods

void LetterTree::addLetter(char newLetter)
{
    if (isalpha(newLetter))
    {
        newLetter = char(toupper(newLetter));
        if (!rootNode)
        {
            rootNode = new LetterNode;
            rootNode->letter = newLetter;
            rootNode->occurrences = 1;
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
        parentNode->occurrences = 1;
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
        std::cout << "File URL: " << fileURL << std::endl << std::endl;
        readFromStream(targetFile);
    }
    else
    {
        std::cout << "File not found or error opening file." << std::endl;
    }

    targetFile.close();

    std::cout << "________ " << targetFile.is_open() << std::endl;

    return 0;
}
