import sys
from sphinx import cmdline

sys.argv.pop(0)
sys.argv.insert(0, "sphinx-build")

cmdline.main(sys.argv)
