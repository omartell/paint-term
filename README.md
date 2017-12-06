# paint-term

## Usage

Run the program with the following command:

    $ ./bin/run.sh

Or with leiningen:

    $ lein run

Or run the included jar directly:

    $ java -jar target/uberjar/paint-term

## Running the tests

Run both integration and unit tests with leiningen:

    $ lein test

## Things to improve:

- Using spec to provide better error messages to the user
  when the input is not valid.

- Validation to check if the coordinates given by the user
  are defined within the canvas.

## License

Copyright © 2017 Oliver Martell Nunez
