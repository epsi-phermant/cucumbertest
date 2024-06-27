curl -X POST https://c4j.cucumber.io/ci/rest/api/results \
-H 'authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjb20uc21hcnRiZWFyLmN1Y3VtYmVyIiwiYXVkIjoiY2kiLCJjb250ZXh0Ijp7ImxpdmluZ19kb2NfaWQiOjQ3MDJ9LCJpYXQiOjE3MTkzMjUyNDN9.jubWNBr2cjiC5a9n2U8GgbCJMWskMv2zKhIs_4fnZEQ' \
-H 'content-type: multipart/form-data' \
-F 'results_files[]'=@results1.json \
-F 'results_files[]'=@results2.json \
-F language=jvm

pause


