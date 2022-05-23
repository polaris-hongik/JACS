# JACS(Java AST Collector for Solidity)
## version 1.2
**What's new?**
1. New function added.( searchByID(String sourceName, long id) )
2. From now on, each node stores the path to the root node.
## version 1.1
**What's new?**
1. New function added. ( getAll() )
2. Some Issue fixed. 
    Added ```IdentifierPath``` to nodeType applicable to ```ModifierInvocation.modifierName``` and ```InheritanceSpecifier.baseName```

## version 1.0

**Parse AST of Solidity to Java Object Structure**

How to Use JACS

```java
import com.chansol.jacs.JACS;
public class Sample {
    public static void main(String [] args){
        JACS jacs = new JACS("Target Directory");
    }
}
```
JACS extracts ASTs for all Solidity source codes in the 'Target Directory'.

JACS 1.0 can extract only ASTs for Source Code written in 0.6.x to 0.8.x versions of Solidity Compiler.

Some errors may be latent because JACS is still in the early stages of development. Therefore, if you think JACS is not working properly, check the debug options to make sure it works properly.

How to Use JACS with debug Option
```java
import com.chansol.jacs.JACS;
public class Sample {
    public static void main(String [] args){
        JACS jacs = new JACS("Target Directory",true);
    }
}
```
The debug option verifies the following factors when JACS collects AST:

* Are all nodes in the AST extracted?
* Are undefined nodes detected?
* Is the Node extracted through the correct object?

If you find any errors through Debug Mode, please let us know so that we can improve JACS.

Contact: chansol53@gmail.com

## Method of JACS
```java
getSourceUnits() : Vector<SourceUnit>  - JACS
```
The getSourceUnits() method returns a Vector containing all SourceUnit nodes that JACS succeeded in extracting.
```java
getFailedList() : Vector<String> - JACS
```
The getFailedList() method returns a Vector containing the path to the files and folders that JACS failed to extract.
```java
getAll(String sourceName) : HashMap<String,Vector<Node>> - JACS
```
Returns the HashSet of nodes separated by NodeType included in SourceName.
```java
getAll(String sourceName, String typeName) : HashMap<String,Vector<Node>> - JACS
```
Returns all nodes corresponding to TypeName included in SourceName.
```java
 searchByID(String sourceName, long id) : Node - jacs
```
Returns the node corresponding to id in sourcename.
## Solidity Node Structure
``` bash
SourceUnit
├── PragmaDirective
├── ImportDirective
└── ContractDefinition[]
       ├── baseContracts : InheritanceSpecifier[]
       └── nodes
              ├── EnumDefinition
              │     └── ...
              ├── FunctionDefinition
              │     └── ...
              ├── StructDefinition
              │     └── ...
              ├── VariableDeclaration
              │     └── ...
              ├── EventDefinition
              │     └── ...
              └── ModifierDefinition
                     └── ...
```

### SourceUnit
Node for the solidity source code and the root node of AST.
### PragmaDirective
Node for the definition of the solubility compiler version that must be applied to the solubility source code.
### ImportDirective
Node for imported contract.
### ContractDefinition
Node for Contract defined in the Solidity Source Code.